package p4_group_8_repo;

import Actor.Actor;
import World.MyStage;
import java.util.ArrayList;
import java.util.Collections;
import Objects.BackgroundImage;
import Objects.Digit;
import Objects.End;
import Objects.Labels;
import Objects.Log;
import Objects.Obstacle;
import Objects.ATurtle;
import Objects.WetTurtle;

/**
 * this class is in charge of initializing all obstacles 
 *
 */
public class Objects {

	MyStage background;
	public ArrayList<Actor> digits;
	ArrayList<Actor> func = new ArrayList<Actor>();
	ArrayList<End> ends = new ArrayList<End>();
	double bonus = 0;

	/**
	 * keeps track of all digits on stage
	 */
	public Objects() {

		digits = new ArrayList<Actor>();

	}

	/**
	 * this method adds the cars and trucks into the array which stores the objects
	 */
	private void addobstacles() {

		func.add(new Obstacle("file:src/resources/truck1" + "Right.png", 0, 1, 120, 120));
		func.add(new Obstacle("file:src/resources/truck1" + "Right.png", 300, 1, 120, 120));
		func.add(new Obstacle("file:src/resources/truck1" + "Right.png", 600, 1, 120, 120));
		func.add(new Obstacle("file:src/resources/car1Left.png", 100, -1, 50, 50));
		func.add(new Obstacle("file:src/resources/car1Left.png", 250, -1, 50, 50));
		func.add(new Obstacle("file:src/resources/car1Left.png", 400, -1, 50, 50));
		func.add(new Obstacle("file:src/resources/car1right.png", 550, -1, 50, 50));
		func.add(new Obstacle("file:src/resources/truck2Right.png", 0, 1, 200, 200));
		func.add(new Obstacle("file:src/resources/truck2Right.png", 500, 1, 200, 200));
		func.add(new Obstacle("file:src/resources/car1Left.png", 500, -5, 50, 50));

	}

	/**
	 * this method adds the 4 goal in the game to the game background
	 */
	private void addends() {
		background.add(new End());
		background.add(new End());
		background.add(new End());
		background.add(new End());

	}

	/**
	 * this method adds the logs into the array which stores the objects
	 */
	private void addlogs() {

		func.add(new Log("file:src/resources/log3.png", 150, 0, 166, 0.75));
		func.add(new Log("file:src/resources/log3.png", 150, 220, 166, 0.75));
		func.add(new Log("file:src/resources/log3.png", 150, 440, 166, 0.75));

		func.add(new Log("file:src/resources/log3.png", 300, 0, 276, -2));
		func.add(new Log("file:src/resources/log3.png", 300, 400, 276, -2));

		func.add(new Log("file:src/resources/log3.png", 150, 50, 329, 0.75));
		func.add(new Log("file:src/resources/log3.png", 150, 270, 329, 0.75));
		func.add(new Log("file:src/resources/log3.png", 150, 490, 329, 0.75));

	}

	/**
	 * this method adds the turtles into the array which stores the objects
	 */
	private void addturtles() {

		func.add(new ATurtle(500, -1, 130, 130));
		func.add(new ATurtle(300, -1, 130, 130));
		func.add(new WetTurtle(700, -1, 130, 130));
		func.add(new WetTurtle(600, -1, 130, 130));
		func.add(new WetTurtle(400, -1, 130, 130));
		func.add(new WetTurtle(200, -1, 130, 130));

	}

	/**
	 * its called all other classes to add all objects in the scene
	 * @param background is the root node of the scene, all objects need to be attached to it as a child
	 * 
	 */
	public void addobjects(MyStage background) {
		this.background = background;
	
		addturtles();
		addlogs();
		addends();
		addobstacles();
		addlabels();

		for (int i = 0; i < ends.size(); i++) {
			background.add(ends.get(i));
		}
		Collections.shuffle(func);

		for (int i = 0; i < func.size(); i++) {
			background.add(func.get(i));
		}

		background.add(new Digit(0, 30, 400, 40));

		BackgroundImage froggerback = new BackgroundImage("file:src/resources/ikogsKW.png");
		background.add(froggerback);
	}

	/**
	 * this method adds the labels on top of the screen
	 */
	private void addlabels() {
		background.add(new Labels("file:src/resources/HighScoreimage.png", 140, 180, 10));
		background.add(new Labels("file:src/resources/Scoreimage.png", 100, 360, 10));
	}

	/**
	 * this method manages the digits used for the score and high score on top of the screen
	 * @param n is the number to be displayed as digits
	 * @param ishigh tells the method if it should update the digits under score or under high score
	 */
	public void setNumber(int n, boolean ishigh) { // set n as digits on screen

		for (int i = 0; i < digits.size(); i++) {

			background.remove(digits.get(i));

		}
		Actor temp;
		int shift = 0;
		int x = 400;
		if (ishigh) {
			x = 270;
		}
		while (n > 0) {
			int d = n / 10;
			int k = n - d * 10;
			n = d;
			temp = new Digit(k, 30, x - shift, 40);
			if (!ishigh) {
				digits.add(temp);
			}
			background.addfront(temp);
			shift += 30;
		}
	}

}
