package com.pqm.mars.util;

public class InvalidMoveException extends Exception {

    public InvalidMoveException() {
        super("Move instruction must be 'F', 'B', 'L' or 'R'");
    }
}
