package org.unitconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unitconverter.model.ConverterType;
import org.unitconverter.model.Unit;
import org.unitconverter.model.UnitConverter;
import org.unitconverter.model.UnitGroup;

import java.util.List;

@RestController
public class UnityRestController {

    @Autowired
    UnitConverter unitConverter;
    @GetMapping("/groups")
    public List<String> getConverterGroups(){

     return unitConverter.getInstalledConverters();

    }

    @GetMapping("/{group}/units")
    public List<String> getUnitsByGroup(@PathVariable String group){

        return unitConverter.getConverterByGroup(group)
                .orElseThrow()
                .getUnits();

    }

    @GetMapping("/{group}/convert")
    public ResultDTO getUnitsByGroup(@PathVariable String group,
                                        @RequestParam double value,@RequestParam String unit,
                                        @RequestParam String toUnit){

        var from = new Unit(value,unit, ConverterType.Distance);
        var result=unitConverter.getConverterByGroup(group)
                .orElseThrow().convert(from,toUnit);

        return new ResultDTO(result.value(),result.unit());



    }



}
