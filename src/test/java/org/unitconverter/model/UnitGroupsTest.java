package org.unitconverter.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class UnitGroupsTest {

    @Test
    public void testArea()
    {
        var gr=new UnitConverter();

        var result = gr.convert("Area","20","SquareMeters","SquareCentimeters");

        org.junit.jupiter.api.Assertions.assertEquals("200000.0",result);

    }
}