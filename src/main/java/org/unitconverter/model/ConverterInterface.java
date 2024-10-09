package org.unitconverter.model;

import java.util.List;

public interface ConverterInterface {

    public String getGroupName();

    public Unit convert(Unit from,String to);

    public List<String> getUnits();

}
