package Objects;

import Actor.*;
import javafx.scene.image.Image;
/**
 * This class is used for the goal
 *
 */
public class End extends Actor {
	boolean activated = false;
	int dim = 60;
	int ypos = 95;
	int xpos[] = { 8, 135, 265, 395 };
	static int i = 0;
	
	/**
	 * Sets the goal has an array to store for each goal
	 */
	public End() { 

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
	 * shows that the player reached the goal
	 */
	public void setEnd() { 
		setImage(new Image("file:src/resources/FrogEnd.png", dim, dim, true, true));
		activated = true;
	}

	/**
	 * used to check if the goal has been activated
	 * @return is a boolean indicating if the hole has been activated or not
	 */
	public boolean isActivated() {
		return activated;
	}

}
