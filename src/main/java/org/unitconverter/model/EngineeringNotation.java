package org.unitconverter.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j

public class EngineeringNotation {

    private final static SIRepresentation neutral = new SIRepresentation("","",0);


    public final static   List<SIRepresentation> prefix = List.of(
           new SIRepresentation("Y","yotta",24),
            new SIRepresentation("Z","zetta",21),
            new SIRepresentation("E","exa",18),
           new SIRepresentation("P","peta",15),
            new SIRepresentation("T","tera",12),
            new SIRepresentation("G","giga",9),
            new SIRepresentation("M","mega",6),
            new SIRepresentation("k","kilo",3),
            neutral,
            new SIRepresentation("m","milli",-3),
            new SIRepresentation("μ","micro",-6),
            new SIRepresentation("n","nano",-9),
            new SIRepresentation("p","pico",-12),
            new SIRepresentation("a","atto",-18),
            new SIRepresentation("z","zepto",-21)
  );

    public SIRepresentation findRepresentationByUnityPrefix(String unit)
    {
        return prefix.stream()
                .filter(e -> unit.startsWith(e.symbol()))
                .findFirst()
                .orElse( neutral );

    }

    public Optional<Unit> convert(Unit from,Unit to){

        if (from.type()!=to.type())
        {
            log.error("converter dos not match");
            return Optional.empty();
        }

        var v1= findRepresentationByUnityPrefix(from.unit());
        var v2 =findRepresentationByUnityPrefix(to.unit());

        var exp = v1.base10()+ v2.base10();

        return Optional.of(new Unit(from.value()*Math.pow(10,exp),to.unit(),to.type()));

    }

    public Unit convertToNeutral(Unit value)
    {
        var v1= findRepresentationByUnityPrefix(value.unit());
        return new Unit(value.value()* v1.value(),value.unit(),value.type());
    }



}


