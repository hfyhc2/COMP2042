package Objects;

import Actor.*;
import javafx.scene.image.Image;

/**
 * This class is used for the highscore and score label at the top of the screen
 * and has no runtime functionality
 * 
 * @author khaled
 *
 */
public class Labels extends Actor {

	int dim = 100; // dimensions
	Image im1;

	// gets p4_group_8_repo for numbers and puts it in place
	public Labels(String imageLink, int dim, int xpos, int ypos) {

		actor = new ActorIdle();

		setImage(new Image(imageLink, dim, dim, true, true));
		setX(xpos);
		setY(ypos);
	}
}
