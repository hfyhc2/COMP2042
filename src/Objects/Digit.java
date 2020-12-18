package Objects;

import Actor.*;
import javafx.scene.image.Image;
/**
 * this class is for the digits used for score 
 *
 */

public class Digit extends Actor {
	int dim; 
	Image im1;
	
	public Digit(int n, int dim, int x, int y) {

		actor = new ActorIdle();
		im1 = new Image("file:src/resources/" + n + ".png", dim, dim, true, true);
		setImage(im1);
		setX(x);
		setY(y);
	}

}
