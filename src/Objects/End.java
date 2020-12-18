package Objects;

import Actor.*;
import javafx.scene.image.Image;

public class End extends Actor {
	boolean activated = false;
	int dim = 60;
	int ypos = 95;
	int xpos[] = { 8, 135, 265, 395 };
	static int i = 0;

	/**
	 * The constructor initiaites the functionality and has an array that stores the
	 * positions that each hole should be in
	 */
	public End() { // initialize end hole

		actor = new ActorIdle();

		setX(xpos[i]);
		setY(ypos);
		i++;
		if (i == 5) {
			i = 0;
		}
		setImage(new Image("file:src/resources/End.png", dim, dim, true, true));
	}

	/**
	 * used to trigger the hole once the player has reached it
	 */
	public void setEnd() { // add frog image to indicate it triggered
		setImage(new Image("file:src/resources/FrogEnd.png", dim, dim, true, true));
		activated = true;
	}

	/**
	 * used to check if the hole has been activated
	 * 
	 * @return a boolean indicating if the hole has been activated or not
	 */
	public boolean isActivated() { // tells other classes if hole is activated or not
		return activated;
	}

}