package com.pqm.mars.pojo;

public class Rover {

    public static final String ROVER_REPORT = "(%d, %d) %s";
    public static final String ROVER_STOPPED = " STOPPED";

    private Coordinates coordinates;

    private Direction direction;

    private boolean stopped;

    public Rover(int xCoordinate, int yCoordinate, Direction direction) {
        this.coordinates = new Coordinates(xCoordinate, yCoordinate);
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public int getX() {
        return this.coordinates.getXCoordinate();
    }

    public int getY() {
        return this.coordinates.getYCoordinate();
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setX(int x) {
        this.coordinates.setXCoordinate(x);
    }

    public void setY(int y) {
        this.coordinates.setYCoordinate(y);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    @Override
    public String toString() {
        String response = String.format(ROVER_REPORT, this.coordinates.getXCoordinate(),
                this.coordinates.getYCoordinate(), this.getDirection().getStringValue());
        if (isStopped())
            response = response.concat(ROVER_STOPPED);
        return response;
    }

}
