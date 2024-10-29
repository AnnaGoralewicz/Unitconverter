package org.unitconverter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unitconverter.model.UnitConverter;

import java.util.List;

@RestController
public class UnityRestController {

    @Autowired
    UnitConverter unitConverter;

    @Operation(summary = "Retrieves all available converter groups")
    @GetMapping("/groups")
    public GroupsDTO getConverterGroups(){

     return new GroupsDTO(unitConverter.groups().stream().toList());

    }

    @Operation(summary = """
            Each group has a mapping of its owen available units. This endpoint delivers
            a list of all available units for a specific group. 
            """)
    @GetMapping("/{group}/units")
    public UnitsDTO getUnitsByGroup(@PathVariable String group){

        return new UnitsDTO(group,unitConverter.units(group));

    }
    @Operation(summary = """
            This endpoint converts between units in a group. To get the result it needs
            the group name, and in the request parameter it needs the value as double encoded ,
            the source unit and the destination unit as well.
            the unit is a string and can be retrieved by the other endpoints.
            """)
    @GetMapping("/{group}/convert")
    public ResultDTO getUnitsByGroup(@PathVariable String group,
                                        @RequestParam double value,@RequestParam String unit,
                                        @RequestParam String toUnit){



        return new ResultDTO(unitConverter.convert(group,value,unit,toUnit), toUnit);



    }



}
