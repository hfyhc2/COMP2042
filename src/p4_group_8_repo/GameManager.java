package p4_group_8_repo;

import Highscore.ScoreHandler;
import World.MyStage;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

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
	 * the constructor gets reference to all necessary classes
	 * 
	 * @param VM a reference to the view manager which has all the references needed
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
	 * This sets up the game scene it does the following tasks: creates player
	 * object animal created player controller and attaches the player to it starts
	 * the music timer adds the initial score and hiscore
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
	 * this method starts a game timer for player related functions
	 */
	private void createTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				PH.checkcontrol();
				if (frog.changeScore()) { // if theres been a change in teh score
					objects.setNumber(frog.getPoints(), false);
				}
				if (frog.getStop()) { // stops everything and alerts player if all 5 ends are filled
					StopFunctions();
				}
			}

		};
	}

	/**
	 * This method runs when the game ends and it stops all game functions from
	 * living and calls the next action(either to get next level or the win screen)
	 */
	private void StopFunctions() {
		background.stopMusic();
		background.stop();
		timer.stop();
		VG.createwin();
	}

}