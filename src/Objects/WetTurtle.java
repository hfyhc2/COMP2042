package Objects;

import Actor.*;
import javafx.scene.image.Image;
/**
 * this class moves the wet turtles 
 *
 */
public class WetTurtle extends Actor {

	int dim = 100;
	
	/**
	 * assigns the wet turtle to a position
	 * @param imageLink the sprite of the wet turtle
	 * @param s speed of wet turtle
	 * @param w width of wet turtle image
	 * @param h height of wet turtle image
	 */
	public WetTurtle(int xpos, int s, int w, int h) {
		int temp = 0;
		int ypos = 0;

		actor = new Turtle();
		image1 = new Image("file:src/resources/TurtleAnimation1.png", dim, dim, true, true);
		image2 = new Image("file:src/resources/TurtleAnimation2Wet.png", dim, dim, true, true);
		image3 = new Image("file:src/resources/TurtleAnimation3Wet.png", dim, dim, true, true);
		image4 = new Image("file:src/resources/TurtleAnimation4Wet.png", dim, dim, true, true);
		type = "TurtleLeft+String.valueOf(s)";


		if (temp != 0) {
			ypos = temp;
		}

		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(image2);
	}

	/**
	 * checks if wet turtle sunk or not 
	 * @return boolean to check turtle is sunk or not
	 */
	public boolean isSunk() {
		return sunk;
	}

	/**
	 * method to check the speed of turtle
	 * @return double which is the speed of the turtle
	 */
	public double getspeed() {
		return speed;
	}
}
