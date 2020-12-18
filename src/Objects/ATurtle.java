package Objects;

import Actor.*;
import javafx.scene.image.Image;
/**
 * this class moves the turtles 
 *
 */
public class ATurtle extends Actor {

	int dim = 110;

	/**
	 * assigns the turtle to a position
	  * @param imageLink the sprite of the turtle
	 * @param s speed of turtle
	 * @param w width of turtle image
	 * @param h height of turtle image
	 */
	public ATurtle(int xpos, int s, int w, int h) {
		int temp = 0;
		int ypos = 0;

		actor = new Turtle();
		image1 = new Image("file:src/resources/TurtleAnimation1.png", dim, dim, true, true);
		image2 = new Image("file:src/resources/TurtleAnimation2.png", dim, dim, true, true);
		image3 = new Image("file:src/resources/TurtleAnimation3.png", dim, dim, true, true);
		image4 = null;
		type="TurtleLeft+String.valueOf(s)";

		if (s > 0) {

			image1 = new Image("file:src/resources/TurtleAnimation1mirror.png", dim, dim, true, true);
			image2 = new Image("file:src/resources/TurtleAnimation2mirror.png", dim, dim, true, true);
			image3 = new Image("file:src/resources/TurtleAnimation3mirror.png", dim, dim, true, true);
			image4 = null;
			type="TurtleRight+String.valueOf(s)";

		}

		if (temp != 0) {
			ypos = temp;
		}
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(image2);
	}
	
	/**
	 * allow the player to move with the turtle
	 * @return the speed of the turtle
	 */
	public double getspeed() {
		return speed;
	}
}
