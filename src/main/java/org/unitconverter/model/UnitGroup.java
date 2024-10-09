package org.unitconverter.model;


import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class UnitGroup {



    String name;
    List<Unit> units;

}
