package com.pqm.mars.pojo;

public enum Direction {
    N("NORTH"), S("SOUTH"), E("EAST"), W("WEST");

    private String strValue;

    Direction(String value){
        this.strValue = value;
    }

    public String getStringValue() {
        return this.strValue;
    }

    public static Direction getEnumValue(char direction) {
        return Direction.valueOf(Character.toString(direction));
    }
}