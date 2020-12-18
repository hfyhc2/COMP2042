package Objects;

import Actor.*;
import javafx.scene.image.Image;

public class ATurtle extends Actor {

	int dim = 110;

	/**
	 * the constructor assigns images of the trutle and a type to be used to
	 * determine position
	 * 
	 * @param xpos position of object on the x axis
	 * @param s    the speed of the object
	 */
	public ATurtle(int xpos, int s, int w, int h) {
		int temp = 0;
		int ypos = 0;

		actor = new Turtle();

		// import turtle sprites

		image1 = new Image("file:src/resources/TurtleAnimation1.png", dim, dim, true, true);
		image2 = new Image("file:src/resources/TurtleAnimation2.png", dim, dim, true, true);
		image3 = new Image("file:src/resources/TurtleAnimation3.png", dim, dim, true, true);
		image4 = null;
		type = "Turtle" + String.valueOf(s);

		if (s > 0) {

			image1 = new Image("file:src/resources/TurtleAnimation1mirror.png", dim, dim, true, true);
			image2 = new Image("file:src/resources/TurtleAnimation2mirror.png", dim, dim, true, true);
			image3 = new Image("file:src/resources/TurtleAnimation3mirror.png", dim, dim, true, true);

		}

		if (temp != 0) {
			ypos = temp;
		}
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(image2);
	}

	public double getspeed() {
		return speed;
	}
}