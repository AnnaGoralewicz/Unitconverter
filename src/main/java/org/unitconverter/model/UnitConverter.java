package org.unitconverter.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unitconverter.model.distance.DistanceConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j

public class UnitConverter {

    private final List<ConverterInterface> unitGroups = List.of(
            new DistanceConverter()
    );
    public UnitConverter()
    {
        log.info("load units");



    }

    public List<String> getInstalledConverters()
    {
        return unitGroups.stream().map(ConverterInterface::getGroupName).toList();
    }

    public Optional<ConverterInterface> getConverterByGroup(String group)
    {
        return unitGroups.stream().filter( ug-> ug.getGroupName().compareToIgnoreCase(group)==0).findAny();

    }



}