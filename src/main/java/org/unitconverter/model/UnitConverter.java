package org.unitconverter.model;

import com.digidemic.unitof.UnitOf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UnitConverter {

    enum direction {
        from,
        to
    }
    record ConverterMethod(String group,Object instance , Class cl, Set<Method> methods ){};


    Map<String,Class> groups;
    Map<String,ConverterMethod> converterMethods;

    public UnitConverter()
    {


        log.info("init groups");

        groups=Arrays.stream(UnitOf.class.getClasses())
                .collect(Collectors
                        .toMap(Class::getSimpleName, v -> v));

        converterMethods=groups.values().stream()
                .map(c -> new ConverterMethod(c.getSimpleName(),instantiate(c).orElse(null)
                                    ,c
                                    ,unitMethodsByClass(c))
                        )
                .filter(converter -> converter.instance!= null)
                .collect(Collectors.toMap(
                        ConverterMethod::group,v-> v));


    }

    public Set<String> groups()
    {
        return  groups.keySet();
    }

    private Set<Method> unitMethodsByClass(Class cl)
    {
        return Arrays.stream(cl.getMethods())
                .filter(m -> m.getName().startsWith("from") || m.getName().startsWith("to"))
                .collect(Collectors.toSet());

    }


    private Optional<Object> instantiate(Class cl)
    {

        try {
            log.info("instantiate {}",cl.getSimpleName());
            return Optional.of(cl.getDeclaredConstructor().newInstance());

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Cant instantiate ");
            return Optional.empty();
        }
    }

    public List<String> units(String group)
    {
        return  converterMethods.get(group)
                .methods.stream()
                .map(Method::getName)
                .filter(name-> name.startsWith("from"))
                .map(name -> name.substring(4))
                .sorted()
                .toList();
    }

    public String convert(String group, String value , String from , String to)
    {
        var converter=converterMethods.get(group);

        var fromMethod= converter.methods.stream()
                .filter(method -> method.getName().compareTo("from"+from)==0)
                .findFirst()
                .orElseThrow();
        var toMethod= converter.methods.stream()
                .filter(method -> method.getName().compareTo("to"+to)==0)
                .findFirst()
                .orElseThrow();

        log.info(Arrays.stream(fromMethod.getParameterTypes()).findFirst().orElseThrow().getSimpleName());

        try {
            var fromObjectConverter=switch (Arrays.stream(fromMethod.getParameterTypes()).findFirst().orElseThrow().getSimpleName()){
                case "double" -> fromMethod.invoke(converter.instance,Double.valueOf(value));
                case "integer" -> fromMethod.invoke(converter.instance,Integer.valueOf(value));
                default ->  fromMethod.invoke(converter.instance,value);
            };

            return toMethod.invoke(fromObjectConverter).toString();

        } catch (IllegalAccessException | InvocationTargetException e) {
            log.warn("cant invoke converter",e);
            throw new RuntimeException(e);
        }


    }

}


