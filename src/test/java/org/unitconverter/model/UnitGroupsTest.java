package org.unitconverter.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class UnitGroupsTest {

    @Test
    public void testGetGroups()
    {
        var gr=new UnitConverter();

        gr.groups().forEach(g -> log.info(g));

    }
}