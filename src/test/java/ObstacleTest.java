import com.pqm.mars.controller.RoverController;
import com.pqm.mars.controller.RoverControllerImpl;
import com.pqm.mars.pojo.Coordinates;
import com.pqm.mars.pojo.Direction;
import com.pqm.mars.pojo.Rover;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class ObstacleTest {

    private static final int x = 3;
    private static final int y = 1;
    private static final char direction = 'E';
    private static final Direction directionEnum = Direction.getEnumValue(direction);
    private static final String validInstruction = "FFLFB";

    private RoverController roverController;
    private Rover rover;
    private HashSet<Coordinates> obstacles;

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Testing: " + description.getMethodName());
        }
    };

    @Before
    public void before() throws Exception {
        this.roverController = new RoverControllerImpl();
        this.obstacles = new HashSet<Coordinates>();
        obstacles.add(new Coordinates(2, 1));
        obstacles.add(new Coordinates(4, 8));
        obstacles.add(new Coordinates(5, 1));
        this.rover = this.roverController.create(x, y, direction, obstacles);
    }

    @Test
    public void moveOneStepBackwardHitsObstacle() throws Exception {
        this.roverController.move(rover, 'B');
        assertEquals(String.format(Rover.ROVER_REPORT, x, y, directionEnum.getStringValue()).concat(Rover.ROVER_STOPPED),
                rover.toString());
    }

    @Test
    public void moveTwoStepForwardHitsObstacle() throws Exception {
        this.roverController.execute(rover, "FF");
        assertEquals(String.format(Rover.ROVER_REPORT, x + 1, y, directionEnum.getStringValue()).concat(Rover.ROVER_STOPPED),
                rover.toString());
    }

    @Test
    public void executeInstructionHitsObstacle() throws Exception {
        this.roverController.execute(rover, validInstruction);
        assertEquals(String.format(Rover.ROVER_REPORT, x + 1, 1, Direction.N.getStringValue()).concat(Rover.ROVER_STOPPED),
                rover.toString());
    }

    @Test
    public void executeInstructionWithoutObstacle() throws Exception {
        this.rover = this.roverController.create(10, 11, direction, obstacles);
        this.roverController.execute(rover, validInstruction);
        assertEquals(String.format(Rover.ROVER_REPORT, 12, 11, Direction.N.getStringValue()), rover.toString());
    }

}
