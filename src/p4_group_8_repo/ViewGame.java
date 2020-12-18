package p4_group_8_repo;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import java.util.*;
import Highscore.ScoreHandler;
import World.MyStage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewGame {
	public Scene menuscene, infoscene;

	int xres = 500;
	int yres = 800;

	String username;

	Scene gamescene;

	public Stage primaryStage;
	public MyStage background;
	public Objects objects;
	public Frog frog;
	GameManager GM;
	public ScoreHandler SH;

	Scene back;

	// private TableView table;

	AnimationTimer timer;

	/**
	 * The constructor instantiates all nessecary objects for the scenes and sets
	 * the menu scene to display
	 * 
	 * @param PrimaryStage This is the base scene that is used to change whats
	 *                     displayed
	 */
	public ViewGame(Stage PrimaryStage) {
		this.primaryStage = PrimaryStage;
		primaryStage.setResizable(false);

		objects = new Objects();
		SH = new ScoreHandler();

		menuscene = CreatMenu();
		infoscene = CreateInfo();

		primaryStage.setScene(CreatMenu());
		primaryStage.setTitle("GAME");
		primaryStage.show();

	}

	/**
	 * This function is called to create the menu scene
	 * 
	 * @return A reference to the menu scene that can be used to set the primary
	 *         stage to
	 */
	private Scene CreatMenu() {

		Label label1 = new Label("Welcome to FROGGER");
		label1.setFont(new Font("Arial", 32));

		Button button1 = new Button("PLAY!");
		button1.setOnAction(e -> primaryStage.setScene(creategamescene()));
		button1.setMinSize(150, 70);

		button1.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");
		// button1.setStyle("-fx-background-color:Green");

		Button button2 = new Button("Exit :(");
		button2.setOnAction(e -> primaryStage.close());
		button2.setMinSize(150, 70);
		button2.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");

		Button button3 = new Button("info");
		button3.setOnAction(e -> primaryStage.setScene(infoscene));
		button3.setMinSize(150, 70);
		button3.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");

		Button button4 = new Button("Scoreboard");
		button4.setOnAction(e -> primaryStage.setScene(createtable()));
		button4.setMinSize(150, 70);
		button4.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");

		VBox layout1 = new VBox();
		layout1.setSpacing(50);
		layout1.setAlignment(Pos.CENTER);// Changed the alignment to center-left
		layout1.getChildren().addAll(label1, button1, button3, button4, button2);
		layout1.setBackground(
				new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene menuscene = new Scene(layout1, xres, yres);
		back = menuscene;
		return menuscene;
	}

	/**
	 * This method is used to create an info screen scene
	 * 
	 * @return a reference to the infor screen scene to set the primary stage to
	 */
	private Scene CreateInfo() {
		Button button4 = new Button("Back");
		button4.setOnAction(e -> primaryStage.setScene(menuscene));
		button4.setMinSize(100, 50);
		button4.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");

		Label label1 = new Label("Get to the end!!!!!!!");
		label1.setFont(new Font("Arial", 32));

		Label label2 = new Label("Move with WASD and");
		label2.setFont(new Font("Arial", 32));

		VBox layout2 = new VBox();
		layout2.getChildren().addAll(label2, label1, button4);
		layout2.setSpacing(50);
		layout2.setBackground(
				new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		layout2.setAlignment(Pos.CENTER);// Changed the alignment to center-left
		return (new Scene(layout2, xres, yres));
	}

	/**
	 * This method creates the game scene and starts everything required for the
	 * game to run
	 * 
	 * @return A referene to the game scene that the primary stage can be set to
	 * @see {@link InitObjects#InitObjects()}
	 *      {@link sessionHandler#sessionHandler(ViewManager)}
	 */
	Scene creategamescene() {
		background = new MyStage(); // stage is aplication window
		Scene scene = new Scene(background, xres, yres);// change x to 500, maybe not here
		// creates new scene with background as root node
		background.start();
		objects.addobjects(background);
		GM = new GameManager(this);
		frog = GM.frog;
		return (scene);
	}

	/**
	 * This method creates a scene with a table that shows the highest 10 scores of
	 * previous players
	 * 
	 * @return a reference to the table scene that the primary stage can be set to
	 */
	public Scene createtable() {
		TableView table = new TableView();
		table.setMaxSize(300, 400);
		ArrayList scores = new ArrayList(SH.getscore());

		TableColumn<ScoreHandler, String> namecol = new TableColumn<>("Name");
		TableColumn<ScoreHandler, String> scorecol = new TableColumn<>("Score");
		TableColumn<ScoreHandler, String> lvlcol = new TableColumn<>("Level");
		scorecol.setSortType(TableColumn.SortType.DESCENDING);
		table.setFixedCellSize(30);
		table.setEditable(false);
		namecol.setMinWidth(100);
		scorecol.setMinWidth(100);
		lvlcol.setMinWidth(100);

		namecol.setCellValueFactory(new PropertyValueFactory<>("Username"));
		scorecol.setCellValueFactory(new PropertyValueFactory<>("Score"));
		lvlcol.setCellValueFactory(new PropertyValueFactory<>("Level"));

		for (int j = 0; j < scores.size(); j++) {
			if (j <= 10) {
				table.getItems().add(scores.get(j));
			}
		}

		table.getColumns().addAll(namecol, scorecol, lvlcol);
		table.getSortOrder().add(scorecol);

		Button button4 = new Button("Back");
		button4.setOnAction(e -> primaryStage.setScene(back));
		button4.setMinSize(100, 50);
		button4.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");

		VBox layout2 = new VBox();
		layout2.getChildren().addAll(table, button4);
		layout2.setSpacing(50);
		layout2.setBackground(
				new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		layout2.setAlignment(Pos.CENTER);// Changed the alignment to center-left
		return (new Scene(layout2, xres, yres));

	}

	/**
	 * This method creates and sets a win screen for the player that apears when the
	 * player completes 10 levels
	 */
	public void createwin() {

		Label label1 = new Label("YOU WIN!!.");
		label1.setFont(new Font("Arial", 32));

		Label label2 = new Label("Score: " + frog.getPoints());
		label1.setFont(new Font("Arial", 32));

		Button button4 = new Button("Scoreboard");
		button4.setOnAction(e -> primaryStage.setScene(createtable()));
		button4.setMinSize(150, 70);
		button4.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");

		Button button2 = new Button("Exit");
		button2.setOnAction(e -> primaryStage.close());
		button2.setMinSize(150, 70);
		button2.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");

		TextField textField = new TextField();
		textField.setMaxSize(150, 70);

		Button button3 = new Button("Submit username");
		button3.setMinSize(150, 70);
		button3.setStyle("-fx-text-fill: #00ff00;-fx-background-color:Green");

		button3.setOnAction(action -> {
			SH.writescores(textField.getText(), frog.getPoints(), background.lvl);
		});

		VBox layout1 = new VBox();
		layout1.setSpacing(50);
		layout1.setAlignment(Pos.CENTER);// Changed the alignment to center-left
		layout1.getChildren().addAll(label1, label2, textField, button3, button4, button2);
		layout1.setBackground(
				new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene winscene = new Scene(layout1, xres, yres);
		back = winscene;
		primaryStage.setScene(winscene);
	}

}
