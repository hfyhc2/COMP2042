package Actor;

import javafx.scene.image.Image;
/**
 * This functionality is for player
 *
 */

public class MainPlayer implements ActorInterface {

	/**
	 * This method calls  the restrict movement method to stops player from going off screen 	
	 * and it checks for the death triggers and loads the animations
	 * 
	 */
	@Override
	public void actor(Actor actor, long now) {

		restrictmovement(actor);
		if (actor.carDeath) {
			actor.image1 = new Image("file:src/resources/cardeath1.png", actor.imgSize, actor.imgSize, true, true);
			actor.image2 = new Image("file:src/resources/cardeath2.png", actor.imgSize, actor.imgSize, true, true);
			actor.image3 = new Image("file:src/resources/cardeath3.png", actor.imgSize, actor.imgSize, true, true);
			actor.image4 = null;
			actor.handledeath(now);
		}

		if (actor.waterDeath) {
			actor.image1 = new Image("file:src/resources/waterdeath1.png", actor.imgSize, actor.imgSize, true, true);
			actor.image2 = new Image("file:src/resources/waterdeath2.png", actor.imgSize, actor.imgSize, true, true);
			actor.image3 = new Image("file:src/resources/waterdeath3.png", actor.imgSize, actor.imgSize, true, true);
			actor.image4 = new Image("file:src/resources/waterdeath4.png", actor.imgSize, actor.imgSize, true, true);
			actor.handledeath(now);
		}

	}
	/**
	 * stops player from going off screen
	 */

	@Override
	public void restrictmovement(Actor actor) {
		if (actor.getY() < 0 || actor.getY() > 734) { // stops player from going offscreen down or up
			actor.setX(300);
			actor.setY(679.8 + actor.movement);
		}
		if (actor.getX() < 0) {// stops player from going offscreen to the left
			actor.move(actor.movement * 2, 0);
		}

		if (actor.getX() > 500 - actor.imgSize / 2) {// stops player from going offscreen to the right
			actor.move(-actor.movement * 2, 0);
		}

	}

}
