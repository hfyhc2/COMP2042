package p4_group_8_repo;

import java.util.ArrayList;

import Actor.Actor;
import Actor.MainPlayer;
import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Objects.End;
import Objects.Log;
import Objects.Obstacle;
import Objects.ATurtle;
import Objects.WetTurtle;

/**
 * Objects of this class represent the player
 * 
 * @author khaled
 *
 */

public class Frog extends Actor {

	int spawnposy = 700; // spawn position
	int end = 0; // counter for end goals, max 5
	public boolean noMove = false; // locks player movement
	public boolean changeScore = false; // theres been a chage in the score that should be added
	int carD = 0; // animation counter
	double w = 800;// stores highest point player reached

	/**
	 * This is the constructor of the animal class it sets the player sprite and the
	 * spawn position
	 */
	public Frog() { // init player
		actor = new MainPlayer();
		imgSize = 40;
		setImage(new Image("file:src/resources/froggerUp.png", imgSize, imgSize, true, true));
		resetPlayerPos();
		movement = 18;
	}

	/**
	 * This method is called to by the PlayerController script constantly to check
	 * for any collisions between the player and other objects
	 * 
	 * @see {@link PlayerController}
	 */
	protected void checkIntersections() {

		if (getIntersectingObjects(Obstacle.class).size() >= 1) { // if player hits car
			carDeath = true;
		}

		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) { // move player with log
			move(getIntersectingObjects(Log.class).get(0).getspeed(), 0);

		} else if (getIntersectingObjects(ATurtle.class).size() >= 1 && !noMove) { // move player with turtle
			move(getIntersectingObjects(ATurtle.class).get(0).getspeed(), 0);
		}

		// if player on turtle and turtle is sunk then kill player
		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				move(getIntersectingObjects(WetTurtle.class).get(0).getspeed(), 0);
			}
		}

		// if intersecting with the end hole
		else if (getIntersectingObjects(End.class).size() >= 1) {

			// if end hole is already activated, undo score additoin || can be more
			// effecient
			if (!getIntersectingObjects(End.class).get(0).isActivated()) {

				setHoleActive();
				w = 800; // resets highest level to minimum
				resetPlayerPos();

			}
		}

		else if (getY() < 200) { // if you are not on any object after this point u ded :)
			waterDeath = true;
		}
	}

	/**
	 * This method handles the event where the player reaches an end point, it will
	 * add points and set the appropriate end hole to active
	 */
	private void setHoleActive() {
		// add score
		points += 50;
		changeScore = true;

		getIntersectingObjects(End.class).get(0).setEnd(); // sets hole to activated
		end++;

	}

	/**
	 * This is a death handler method, it plays the animation according to the type
	 * of death, which is checked in the ActPlayer class it uses the game timer to
	 * go through death sprites
	 * 
	 * @param now this is the game timer it also handles anything required for
	 *            player death, such as point deduction and position reset
	 */
	public void handledeath(long now) {
		noMove = true;// lock movement

		// play death animation
		if ((now) % 11 == 0) {
			carD++;
		}
		if (carD == 1) {
			setImage(image1);
		}
		if (carD == 2) {
			setImage(image2);
		}
		if (carD == 3) {
			setImage(image3);
		}
		if (carD == 4) {
			if (image4 != null) {
				setImage(image4);
			}
		}
		if (carD == 5) {
			// reset position player
			resetPlayerPos();

			// reset death triggers
			waterDeath = false;
			carDeath = false;

			// reset animation
			carD = 0;
			setImage(new Image("file:src/resources/froggerUp.png", imgSize, imgSize, true, true)); // reset player
																									// sprite
			noMove = false; // unlock movement
			if (points > 50) { // deduct points if possible
				points -= 50;
				changeScore = true;
			}
		}
	}

	/**
	 * This method resets the player position to spawn point
	 */
	private void resetPlayerPos() {
		setX(300);
		setY(spawnposy);
	}

	/**
	 * This is a public method that can be used to check if the player won depending
	 * on how many end points it reached its called to from SessionHandler
	 * 
	 * @return boolean whether the player met winning conditions or not
	 * @see sessionHandler
	 */
	public boolean getStop() {
		return end == 4;
	}

	/**
	 * This is a public method that returns the number of points the player have
	 * gathered
	 * 
	 * @return points, an integer representing the player's points
	 */
	public int getPoints() {// sends points to calsses that requested
		return (points);
	}

	/**
	 * This is a public method that can be called to check whether theres been a
	 * change in the score
	 * 
	 * @return a boolean that indiciates if the score has changed
	 */
	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;

	}

}
