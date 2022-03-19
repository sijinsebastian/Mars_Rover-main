package com.pqm.mars.controller;

import com.pqm.mars.pojo.Move;
import com.pqm.mars.pojo.Coordinates;
import com.pqm.mars.pojo.Direction;
import com.pqm.mars.pojo.Rover;
import com.pqm.mars.util.InvalidDirectionException;
import com.pqm.mars.util.InvalidMoveException;
import com.pqm.mars.util.ObstacleException;

import java.util.HashSet;

public class RoverControllerImpl implements RoverController {

    private ObstacleWatcher obstacleWatcher = new ObstacleWatcher();

    public Rover create(int x, int y, char direction) throws InvalidDirectionException {
        try {
            return new Rover(x, y, Direction.getEnumValue(direction));
        } catch (IllegalArgumentException e) {
            throw new InvalidDirectionException();
        }
    }

    public Rover create(int x, int y, char direction, HashSet<Coordinates> obstacles) throws InvalidDirectionException {
        obstacleWatcher = new ObstacleWatcher(obstacles);
        return create(x, y, direction);
    }

    public Rover execute(Rover rover, String command) throws InvalidMoveException, InvalidDirectionException {
        for (int i = 0; i < command.length(); i++)
            rover = move(rover, command.charAt(i));
        return rover;
    }

    public Rover move(Rover rover, char instruction) throws InvalidMoveException, InvalidDirectionException {
        Move nextMove;
        try {
            nextMove = Move.getEnumValue(instruction);
        } catch (IllegalArgumentException e) {
            throw new InvalidMoveException();
        }
        switch (nextMove) {
            case F:
            case B:
                moveOnceStep(rover, nextMove);
                break;
            case L:
            case R:
                rotate(rover, nextMove);
                break;
        }
        return rover;
    }

    private Rover moveOnceStep(Rover rover, Move nextStep) {
        int updatedCoordinate;
        Direction headingDirection = rover.getDirection();

        try {
            updatedCoordinate = rover.getY();
            if (headingDirection == Direction.N)
                updatedCoordinate += ((nextStep == Move.F) ? 1 : -1);
            else if (headingDirection == Direction.S)
                updatedCoordinate += ((nextStep == Move.F) ? -1 : 1);
            obstacleWatcher.checkIfObstacle(new Coordinates(rover.getX(), updatedCoordinate));
            rover.setY(updatedCoordinate);

            updatedCoordinate = rover.getX();
            if (headingDirection == Direction.E)
                updatedCoordinate += ((nextStep == Move.F) ? 1 : -1);
            else if (headingDirection == Direction.W)
                updatedCoordinate += ((nextStep == Move.F) ? -1 : 1);
            obstacleWatcher.checkIfObstacle(new Coordinates(updatedCoordinate, rover.getY()));
            rover.setX(updatedCoordinate);
        } catch (ObstacleException e) {
            rover.setStopped(true);
        }
        return rover;
    }

    private Rover rotate(Rover rover, Move rotation) throws InvalidDirectionException {
        if (rotation == Move.L) {
            rover.setDirection(rotateLeft(rover.getDirection()));
        } else if (rotation == Move.R) {
            rover.setDirection(rotateRight(rover.getDirection()));
        } else {
            throw new InvalidDirectionException("Rotation must be 'R' or 'L'");
        }
        return rover;
    }

    private static Direction rotateLeft(Direction curDirection) {
        switch (curDirection) {
            case N:
                return Direction.W;
            case W:
                return Direction.S;
            case S:
                return Direction.E;
            case E:
                return Direction.N;
            default:
                return null;
        }
    }

    private static Direction rotateRight(Direction curDirection) {
        switch (curDirection) {
            case N:
                return Direction.E;
            case W:
                return Direction.N;
            case S:
                return Direction.W;
            case E:
                return Direction.S;
            default:
                return null;
        }
    }
}
