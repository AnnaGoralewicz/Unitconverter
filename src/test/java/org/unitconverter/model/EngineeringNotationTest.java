package org.unitconverter.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class EngineeringNotationTest {

    @Test
    void findRepresentationByUnityPrefix() {
         var engineeringNotation = new EngineeringNotation();
         var testUnits= EngineeringNotation.prefix.stream()
                 .map( prefix -> prefix.symbol().concat("m")).toList();
         int exp=24;
         for ( var unit : testUnits){
            var finding=engineeringNotation.findRepresentationByUnityPrefix(unit);
            assertEquals (exp, finding.base10());
            assertEquals(Math.pow(10,exp),finding.value());
            assertTrue(unit.startsWith(finding.symbol()));
            if (exp<=0 && exp>-3) {
                exp-=1;
            } else exp-=3;



            }

    }

    @Test
    void convert() {
        var engineeringNotation = new EngineeringNotation();



        var src = new Unit(20,"cm",ConverterType.Distance);

        assertTrue(engineeringNotation.convert(src,new Unit(0,"c",ConverterType.Tempreture))
                        .isEmpty());


        engineeringNotation.convert(src,new Unit(0,"m",ConverterType.Distance))
                .ifPresentOrElse( result -> assertEquals(new Unit(0.2,"m",ConverterType.Distance),result),
                         ()-> fail("empty result"));



        src = new Unit(30,"mm",ConverterType.Distance);
        engineeringNotation.convert(src,new Unit(0,"m",ConverterType.Distance))
                .ifPresentOrElse( result -> assertEquals(new Unit(0.03,"m",ConverterType.Distance),result),
                        ()-> fail("empty result"));

        src = new Unit(40,"m",ConverterType.Distance);
        engineeringNotation.convert(src,new Unit(0,"km",ConverterType.Distance))
                .ifPresentOrElse( result -> assertEquals(new Unit(0.04,"km",ConverterType.Distance),result),
                        ()-> fail("empty result"));

    }

    @Test
    void convertToNeutral() {

        var engineeringNotation = new EngineeringNotation();
        var src = new Unit(20,"cm",ConverterType.Distance);
         engineeringNotation.convertToNeutral(src)
                .ifPresentOrElse( result -> assertEquals(new Unit(0.2,"m",ConverterType.Distance),result),
                        ()-> fail("empty result"));

    }
}