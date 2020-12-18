package p4_group_8_repo;

import Highscore.ScoreHandler;
import World.MyStage;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
/**
 *  starts and ends the game session
 *
 */
public class GameManager {
	
	public MyStage background;
	Stage PS;
	public Frog frog;
	Objects objects;
	ViewGame VG;
	ScoreHandler SH;
	AnimationTimer timer;
	PlayerHandler PH;

	/**
	 * gets reference to all the necessary classes
	 * @param VG is a reference for ViewGame
	 */
	public GameManager(ViewGame VG) {
		this.background = VG.background;
		this.PS = VG.primaryStage;
		this.objects = VG.objects;
		this.VG = VG;
		this.SH = VG.SH;
		start();

	}

	/**
	 * creates player object frog and attach itself to player handler
	 * starts the music 
	 * update the initial score and high score
	 */
	public void start() {
		frog = new Frog();
		background.addfront(frog);
		PH = new PlayerHandler(frog);
		background.playMusic();
		createTimer();
		objects.setNumber(SH.gethiscore(), true);
		objects.setNumber(frog.getPoints(), false);
		timer.start();
	}

	/**
	 * starts the timer for player related functions
	 */
	private void createTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				PH.checkcontrol();
				if (frog.changeScore()) { 
					objects.setNumber(frog.getPoints(), false);
				}
				if (frog.getStop()) { 
					StopFunctions();
				}
			}

		};
	}
	
	/**
	 * stops all game functions and shows win screen
	 */
	private void StopFunctions() {
		background.stopMusic();
		background.stop();
		timer.stop();
		VG.createwinscreen();
	}

}
