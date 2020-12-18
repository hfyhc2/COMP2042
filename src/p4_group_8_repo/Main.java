package p4_group_8_repo;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * teh class starts the game
 *
 */
public class Main extends Application {
	ViewGame viewgame;

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * initialize the class viewgame
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		viewgame = new ViewGame(primaryStage);
	}

}
