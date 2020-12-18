package Objects;

import Actor.*;
import javafx.scene.image.Image;
/**
 * this class moves the log
 *
 */
public class Log extends Actor {

	int dim = 100;
	
	/**
	 * sends the log to class world to check if there is a space for it
	 * @param imageLink is the location of the log image
	 * @param xpos is the starting x coordinate
	 * @param s is the speed the log 
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
	
	/**
	 * allow the player to move with the log
	 * @return the speed of the log
	 */
	public double getspeed() {
		return speed;
	}
}
