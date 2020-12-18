package Actor;

import javafx.scene.image.Image;

/**
 * This functionality is used for turtles and wet turtles
 * 
 * @author khaled
 *
 */
public class Turtle implements ActorInterface {

	/**
	 * aside from movement, this method is also in charge of movement animations
	 */
	public void actor(Actor actor, long now) {

		restrictmovement(actor);

		if (now / 900000000 % 4 == 0) {
			actor.setImage(actor.image2);
			actor.sunk = false;

		} else if (now / 900000000 % 4 == 1) {
			actor.setImage(actor.image1);
			actor.sunk = false;
		} else if (now / 900000000 % 4 == 2) {
			actor.setImage(actor.image3);
			actor.sunk = false;
		} else if (now / 900000000 % 4 == 3) {
			if (actor.image4 != null) {
				actor.setImage(actor.image4);
				actor.sunk = true;
			}
		}

		actor.move(actor.speed, 0);

	}

	@Override
	public void restrictmovement(Actor actor) {// if sprite goes out of frame, put it back at beginning

		if (actor.getX() > 600 && actor.speed > 0)
			actor.setX(-200);
		if (actor.getX() < -75 && actor.speed < 0)
			actor.setX(600);

	}
}
