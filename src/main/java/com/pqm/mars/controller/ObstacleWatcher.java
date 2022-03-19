package com.pqm.mars.controller;

import com.pqm.mars.pojo.Coordinates;
import com.pqm.mars.util.ObstacleException;

import java.util.HashSet;
import java.util.Set;

public class ObstacleWatcher {

    public static final String OBSTACLE_ERROR = "Obstacle at (%d,%d)";
    private HashSet<Coordinates> obstacles;

    public ObstacleWatcher() {
        obstacles = new HashSet<Coordinates>();
    }

    public ObstacleWatcher(Set<Coordinates> obstacles) {
        this.obstacles = (HashSet<Coordinates>) obstacles;
    }

    public void checkIfObstacle(Coordinates coordinates) throws ObstacleException {
        if (obstacles.contains(coordinates))
            throw new ObstacleException(String.format(OBSTACLE_ERROR,
                    coordinates.getXCoordinate(), coordinates.getYCoordinate()));
    }

}
