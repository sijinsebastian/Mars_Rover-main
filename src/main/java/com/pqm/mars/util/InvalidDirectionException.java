package com.pqm.mars.util;

public class InvalidDirectionException extends Exception {

    public InvalidDirectionException() {
        super("Rover's direction must be 'N', 'E', 'S' or 'W'");
    }

    public InvalidDirectionException(String message) {
        super(message);
    }
}
