package p4_group_8_repo;

import java.util.Random;
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
 * This class is in charge of initializing all obstacles the player faces in the
 * game
 * 
 * @author khaled
 *
 */
public class Objects {

	MyStage background;

	public ArrayList<Actor> digits;

	ArrayList<Actor> func = new ArrayList<Actor>();
	ArrayList<End> ends = new ArrayList<End>();
	double bonus = 0;

	/**
	 * The constructor does nothing but instatiate a list that keeps track of all
	 * digits on stage
	 */
	public Objects() {

		digits = new ArrayList<Actor>();

	}

	/**
	 * This method returns the bonus speed each obstacle should get according to the
	 * level reached Every level the speed increases by 0.05
	 */
	private void getbonus() {
		for (int i = 0; i < background.lvl; i++) {
			bonus = bonus + 0.05;

		}
	}

	/**
	 * This method instantiates cars and trucks and adds them to to the func array
	 * which stores the game objects
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
	 * This method instantiates the 5 end holes in the game and adds them to the
	 * game background
	 */
	private void addends() {
		background.add(new End());
		background.add(new End());
		background.add(new End());
		background.add(new End());

	}

	/**
	 * This method instantiates the logs and adds them to the func array which
	 * contains the objects
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
	 * This method instantiates the turtles and wet turtles and adds them to the
	 * func arryay which stores the game objects
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
	 * This is the main method of this class and its called to from other classes to
	 * instantiate all objects in the scene
	 * 
	 * @param background This is the root node of the scene, all objects need to be
	 *                   attached to it as a child
	 * 
	 *                   after it runs the instantiating functions, it shuffles the
	 *                   func array which will contain all moving objects and add
	 *                   them to the background afterwards
	 */
	public void addobjects(MyStage background) {
		this.background = background;
		// sets background image |

		getbonus();

		// adding objects to background makes them children nodes

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
	 * This method instatiates the score and hiscore labels on top of the screen
	 */
	private void addlabels() {
		background.add(new Labels("file:src/resources/HighScoreimage.png", 140, 180, 10));
		background.add(new Labels("file:src/resources/Scoreimage.png", 100, 360, 10));
	}

	/**
	 * This method manages the digits used for the score and highscore on top of the
	 * screen
	 * 
	 * @param n      This is the number to be displayed as digits
	 * @param ishigh This boolean tells the method if it should place the digits
	 *               under score or under highscore(if true then under highscore) it
	 *               also deleted all current score digits from the screen before
	 *               updating them
	 */
	public void setNumber(int n, boolean ishigh) { // set n as digits on screen

		for (int i = 0; i < digits.size(); i++) {

			background.remove(digits.get(i));

		}
		Actor temp;
		int shift = 0;// shifts positions for bigger digits
		int x = 400;
		if (ishigh) {
			x = 270;
		}
		while (n > 0) {
			int d = n / 10;
			int k = n - d * 10;// calculations to check number digit by digit
			n = d;
			temp = new Digit(k, 30, x - shift, 40);
			if (!ishigh) {
				digits.add(temp);
			}
			background.addfront(temp);// 360 is end of number, works backwards
			shift += 30;// move back 30 more pixels
		}
	}

}
