package com.pqm.mars.pojo;

public enum Move {

    F("FORWARD"), B("BACKWARD"), L("ROTATE_LEFT"), R("ROTATE_RIGHT");

    private String strValue;

    Move(String strValue) {
        this.strValue = strValue;
    }

    public String getStrValue() {
        return this.strValue;
    }

    public static Move getEnumValue(char move) {
        return Move.valueOf(Character.toString(move));
    }
}