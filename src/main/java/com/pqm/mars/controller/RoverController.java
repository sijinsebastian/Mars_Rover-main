package com.pqm.mars.controller;

import com.pqm.mars.pojo.Rover;
import com.pqm.mars.pojo.Coordinates;
import com.pqm.mars.util.InvalidDirectionException;
import com.pqm.mars.util.InvalidMoveException;

import java.util.HashSet;

public interface RoverController {

    /**
     * Create a rover with parameters
     *
     * @param x         x start coordinates of the rover
     * @param y         y start coordinates of the rover
     * @param direction start direction of the rover
     * @return rover with correct parameters
     * @throws Exception error when direction is not NORTH, SOUTH, EAST, WEST
     */
    Rover create(int x, int y, char direction) throws InvalidDirectionException;

    /**
     * Create a rover with parameters
     *
     * @param x         x start coordinates of the rover
     * @param y         y start coordinates of the rover
     * @param direction start direction of the rover
     * @param obstacles known obstacles for the rover
     * @return rover with correct parameters
     * @throws Exception error when direction is not NORTH, SOUTH, EAST, WEST
     */
    Rover create(int x, int y, char direction, HashSet<Coordinates> obstacles) throws InvalidDirectionException;

    /**
     * Execute the command string passed
     *
     * @param rover   rover instance before movement
     * @param command command for action
     * @return rover instance of state after movement
     */
    Rover execute(Rover rover, String command) throws InvalidMoveException, InvalidDirectionException;

    /**
     * Move the rover to next position or rotate the rover
     *
     * @param rover       rover instance before movement
     * @param instruction instruction for action like move or rotate
     * @return rover instance of state after movement
     */
    Rover move(Rover rover, char instruction) throws InvalidMoveException, InvalidDirectionException;

}
