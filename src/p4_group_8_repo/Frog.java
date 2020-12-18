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
 * the player's objects
 * 
 *
 */

public class Frog extends Actor {
	
	/**
	 * player's spawn point
	 */
	int spawnposy = 700; 
	
	/**
	 * to check if all goals is reached
	 */
	int end = 0; 
	
	/**
	 * locks player movement
	 */
	public boolean noMove = false; 
	
	/**
	 * check if the score need to be updated 
	 */
	public boolean changeScore = false; 
	
	/**
	 * the animation timer
	 */
	int carD = 0; 
	
	/**
	 * used to check highest lane player reached
	 */
	double w = 800;

	/**
	 *  sets the player sprite and the spawn point
	 */
	public Frog() { 
		setX(250);
		actor = new MainPlayer();
		imgSize = 40;
		setImage(new Image("file:src/resources/froggerUp.png", imgSize, imgSize, true, true));
		resetPlayerPos();
		movement = 18;
	}

	/**
	 * to check for any collisions between the player and  objects
	 */
	protected void checkIntersections() {

		if (getIntersectingObjects(Obstacle.class).size() >= 1) { 
			carDeath = true;
		}

		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) { 
			move(getIntersectingObjects(Log.class).get(0).getspeed(), 0);

		} else if (getIntersectingObjects(ATurtle.class).size() >= 1 && !noMove) { 
			move(getIntersectingObjects(ATurtle.class).get(0).getspeed(), 0);
		}

		
		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				move(getIntersectingObjects(WetTurtle.class).get(0).getspeed(), 0);
			}
		}

		
		else if (getIntersectingObjects(End.class).size() >= 1) {

			
			if (!getIntersectingObjects(End.class).get(0).isActivated()) {

				setHoleActive();
				w = 800; 
				resetPlayerPos();

			}
		}

		else if (getY() < 390 ) { 
			waterDeath = true;
		}
	}

	/**
	 * add points and set the appropriate goal to active when player reaches the goal
	 */
	private void setHoleActive() {
		// add score
		points += 50;
		changeScore = true;

		getIntersectingObjects(End.class).get(0).setEnd(); 
		end++;

	}

	/**
	 * plays the animation according to the type of death, handles point deduction and position reset
	 * @param now this is the game timer
	 */
	public void handledeath(long now) {
		noMove = true;
		
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
			
			resetPlayerPos();

			waterDeath = false;
			carDeath = false;

			carD = 0;
			setImage(new Image("file:src/resources/froggerUp.png", imgSize, imgSize, true, true)); 
																								
			noMove = false; 
			if (points > 50) { 
				points -= 50;
				changeScore = true;
			}
		}
	}

	/**
	 *  resets player position to spawn point
	 */
	private void resetPlayerPos() {
		setX(250);
		setY(spawnposy);
	}

	
	public boolean getStop() {
		return end == 4;
	}

	/**
	 * returns the number of points the player have gathered
	 * @return points is an integer representing the player's points
	 */
	public int getPoints() {
		return (points);
	}

	/**
	 * check if the score have change
	 * @return a is a boolean that shows if the score has changed
	 */
	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;

	}

}
