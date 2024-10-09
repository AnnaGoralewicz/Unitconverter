package org.unitconverter.model;

public record SIRepresentation(String symbol,String name,int base10,double value) {
    public SIRepresentation(String symbol,String name,int base10) {

        this(symbol,name,base10,Math.pow(10,base10));
    }
}