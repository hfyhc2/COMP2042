package Objects;

import Actor.*;
import javafx.scene.image.Image;
/**
 * this class moves the cars 
 *
 */
public class Obstacle extends Actor {
	
	/**
	 * assigns the cars to a position
	 * @param imageLink the sprite of the car
	 * @param s speed of car
	 * @param w width of car image
	 * @param h height of car image
	 */
	public Obstacle(String imageLink, int xpos, int s, int w, int h) {
		actor = new ActorMove();
		int ypos = 0;
		int temp = 0;
		if (imageLink == "file:src/resources/car1Left.png") {
			type = "car1left" + String.valueOf(s);
		}
		if (imageLink == "file:src/resources/car1right.png") {
			type = "car1Right" + String.valueOf(s);
		}
		if (imageLink == "file:src/resources/truck2Right.png") {
			type = ("truckRight" + String.valueOf(s));
		}
		if (imageLink == "file:src/resources/truck1Right.png") {
			type = ("truckRight" + String.valueOf(s));
		}

		if (temp != 0) {
			ypos = temp;
		}
		setImage(new Image(imageLink, w, h, true, true));
		speed = s;
	}

}
