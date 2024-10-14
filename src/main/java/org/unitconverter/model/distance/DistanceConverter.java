package org.unitconverter.model.distance;

import lombok.extern.slf4j.Slf4j;
import org.unitconverter.model.ConverterInterface;
import org.unitconverter.model.EngineeringNotation;
import org.unitconverter.model.Unit;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DistanceConverter implements ConverterInterface {

    private final static List<String> sIUnitSuffix= List.of("m");
    private final static List<String> imperialSuffix= List.of("miles","in");

    private final EngineeringNotation engineeringNotation = new EngineeringNotation();

    public DistanceConverter()
    {

    }

    @Override
    public String getGroupName() {
        return "distance";
    }

    @Override
    public Unit convert(Unit from, String to) {
        if (sIUnitSuffix.stream().anyMatch(u -> from.unit().endsWith(u)))
        {


            return engineeringNotation.convert(from,new Unit(0,to,from.type())).orElseThrow();

        }

        return from;

    }

    @Override
    public List<String> getUnits() {

        return sIUnitSuffix.stream()
                .map(u -> EngineeringNotation.prefix.stream().map( si -> si.symbol().concat(u)).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


    }
}
