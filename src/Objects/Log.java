package Objects;

import Actor.*;
import javafx.scene.image.Image;

public class Log extends Actor {

	int dim = 100;

	/**
	 * instatiates log and sends it to world to check a free strip to place it on in
	 * the game using the checknextfree method
	 * 
	 * @param imageLink the location of the log image
	 * @param xpos      the starting x coordinate
	 * @param s         the speed the log moves in
	 * @see World#checknextfree
	 */
	public Log(String imageLink, int size, int xpos, int ypos, double s) {
		actor = new ActorMove();
		int temp = 0;

		setImage(new Image(imageLink, dim, dim, true, true));
		if (s > 0) {
			type = "Logright" + String.valueOf(s);
		} else {
			type = "Logleft" + String.valueOf(s);
		}

		if (temp != 0) {
			ypos = temp;
		}

		setX(xpos);
		setY(ypos);
		speed = s;

	}

	public double getspeed() {
		return speed;
	}
}