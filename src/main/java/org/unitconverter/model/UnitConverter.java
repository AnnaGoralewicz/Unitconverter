package org.unitconverter.model;

import com.digidemic.unitof.UnitOf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UnitConverter {


    Map<String,Class> groups;

    public UnitConverter()
    {


        log.info("init groups");


        groups=Arrays.stream(UnitOf.class.getClasses())
                .collect(Collectors
                        .toMap(Class::getSimpleName, v -> v));

    }

    public Set<String> groups()
    {
        return  groups.keySet();
    }

    public List<String> units(String group)
    {
        return Arrays.stream(groups.get(group).getMethods())
                .map(Method::getName)
                .filter(m -> m.startsWith("from"))
                .map(m-> m.substring(4))
                .toList();


    }

}
