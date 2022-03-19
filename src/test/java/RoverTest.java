import com.pqm.mars.controller.RoverController;
import com.pqm.mars.controller.RoverControllerImpl;
import com.pqm.mars.pojo.Direction;
import com.pqm.mars.pojo.Rover;
import com.pqm.mars.util.InvalidDirectionException;
import com.pqm.mars.util.InvalidMoveException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoverTest {

    private static final int x = 3;
    private static final int y = 1;
    private static final char direction = 'E';
    private static final int negativeX = -3;
    private static final int negativeY = -1;
    private static final char wrongDirection = 'A';

    private static final char rotateRight = 'R';
    private static final char rotateLeft = 'L';
    private static final char wrongCommand = 'Z';
    private static final String validInstruction = "FFLFB";
    private static final String invalidInstruction = "FFLxB";
    private static final Direction directionEnum = Direction.getEnumValue(direction);
    private RoverController roverController;
    private Rover rover;

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Testing: " + description.getMethodName());
        }
    };

    @Before
    public void before() throws InvalidDirectionException {
        this.roverController = new RoverControllerImpl();
        this.rover = this.roverController.create(x, y, direction);
    }


    @Test
    public void createRover() {
        assertEquals(x, rover.getX());
        assertEquals(y, rover.getY());
        assertEquals(directionEnum, rover.getDirection());
        assertNotEquals(rover.getX(), y);
        assertNotEquals(rover.getY(), x);
        assertEquals(String.format(Rover.ROVER_REPORT, x, y, directionEnum.getStringValue()), rover.toString());
    }

    @Test
    public void createRoverWithNegativeCoordinates() throws InvalidDirectionException {
        this.rover = this.roverController.create(negativeX, negativeY, direction);
        assertEquals(negativeX, rover.getX());
        assertEquals(negativeY, rover.getY());
        assertEquals(directionEnum, rover.getDirection());
        assertEquals(String.format(Rover.ROVER_REPORT, negativeX, negativeY, directionEnum.getStringValue()), rover.toString());
    }

    @Test(expected = InvalidDirectionException.class)
    public void createRoverWithWrongDirection() throws InvalidDirectionException {
        this.rover = this.roverController.create(x, y, wrongDirection);
    }

    @Test
    public void moveOneStepForward() throws InvalidDirectionException, InvalidMoveException {
        this.roverController.move(rover, 'F');
        assertEquals(x + 1, rover.getX());
        assertEquals(String.format(Rover.ROVER_REPORT, x + 1, y, directionEnum.getStringValue()), rover.toString());
    }

    @Test
    public void moveOneStepBackward() throws InvalidDirectionException, InvalidMoveException {
        this.roverController.move(rover, 'B');
        assertEquals(x - 1, rover.getX());
        assertEquals(String.format(Rover.ROVER_REPORT, x - 1, y, directionEnum.getStringValue()), rover.toString());
    }

    @Test
    public void rotateOnceRight() throws InvalidDirectionException, InvalidMoveException {
        this.roverController.move(rover, rotateRight);
        assertEquals(Direction.S, rover.getDirection());
        assertEquals(String.format(Rover.ROVER_REPORT, x, y, Direction.S.getStringValue()),
                rover.toString());
    }

    @Test
    public void rotateOnceLeft() throws InvalidDirectionException, InvalidMoveException {
        this.roverController.move(rover, rotateLeft);
        assertEquals(Direction.N, rover.getDirection());
        assertEquals(String.format(Rover.ROVER_REPORT, x, y, Direction.N.getStringValue()),
                rover.toString());
    }

    @Test(expected = InvalidMoveException.class)
    public void rotateWithIncorrectRotation() throws InvalidDirectionException, InvalidMoveException {
        this.roverController.move(rover, wrongCommand);
    }

    @Test
    public void executeInstruction() throws InvalidDirectionException, InvalidMoveException {
        this.roverController.execute(rover, validInstruction);
        assertEquals(Direction.N, rover.getDirection());
        assertEquals(5, rover.getX());
        assertEquals(1, rover.getY());
        assertEquals(String.format(Rover.ROVER_REPORT, 5, 1, Direction.N.getStringValue()),
                rover.toString());
    }

    @Test(expected = InvalidMoveException.class)
    public void executeInvalidInstruction() throws InvalidDirectionException, InvalidMoveException {
        this.roverController.execute(rover, invalidInstruction);
    }

}
