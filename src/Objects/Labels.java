package Objects;

import Actor.*;
import javafx.scene.image.Image;

/**
 * to show the high score and player score on top when game is running
 *
 */
public class Labels extends Actor {

	int dim = 100; 
	Image im1;

	
	public Labels(String imageLink, int dim, int xpos, int ypos) {

		actor = new ActorIdle();

		setImage(new Image(imageLink, dim, dim, true, true));
		setX(xpos);
		setY(ypos);
	}
}
