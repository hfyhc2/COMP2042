package Objects;

import Actor.*;
import javafx.scene.image.Image;

public class Obstacle extends Actor {

	// initialize object
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