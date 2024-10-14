package org.unitconverter.model.distance;

import lombok.extern.slf4j.Slf4j;
import org.unitconverter.model.ConverterInterface;
import org.unitconverter.model.EngineeringNotation;
import org.unitconverter.model.Unit;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
public class TimeConverter implements ConverterInterface {

    private final static List<String> sIUnitSuffix= List.of("s");
    private final static List<String> timeSuffix= List.of("min","h","day");

    @Override
    public String getGroupName() {
        return "time";
    }

    @Override
    public Unit convert(Unit from, String to) {
        return null;
    }

    @Override
    public List<String> getUnits() {
        return Stream.concat(sIUnitSuffix.stream()
                .map(u -> EngineeringNotation.prefix.stream().map(si -> si.symbol().concat(u)).toList())
                .flatMap(Collection::stream),
                timeSuffix.stream())
                .collect(Collectors.toList());


    }
}
