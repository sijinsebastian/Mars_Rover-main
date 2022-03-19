# Mars Rover 

## Requirement

Develop an API that translates commands to instructions that are understood by the rover.

A rover's position and location is represented by a combination of x and y
co-ordinates and a letter representing one of the four cardinal compass points.
The rover is initialised with it’s current coordinates and the direction it is facing. These could be any coordinates, supplied as arguments (x, y, direction)  e.g. (4, 2,  EAST).

The rover is given a command string which contains multiple commands. This string must then be broken into each individual command and that command then executed. The valid commands are:

F -> Move forward on current heading  
B -> Move backwards on current heading  
L -> Rotate left by 90  degrees  
R -> Rotate right by 90 degrees 


● An example command might be  FLFFFRFLB  

● Once the full command string has been followed, the rover reports it’s current coordinates and heading in the format  (6, 4)  NORTH  


Given a set of coordinates for all the known obstacles in the format:
[[1,4], [3,5], [7,4]]
When the next coordinate has an obstacle, stop at the coordinate immediately before and report position, heading and Stopped due to collision, e.g. (3, 4) WEST STOPPED

## The Solution

Use the commandline app at the root folder to execute the below commands.

Build the app using:

```
mvn clean install
```

And then execute the tests with:

 ```
mvn test
 ```

