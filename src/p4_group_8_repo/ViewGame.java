package p4_group_8_repo;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import java.util.*;
import Highscore.ScoreHandler;
import World.MyStage;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * handles all scene related work 
 *
 */
public class ViewGame {
	public Scene menuscene, infoscene;

	int xres = 600;
	int yres = 600;

	String username;

	Scene gamescene;

	public Stage primaryStage;
	public MyStage background;
	public Objects objects;
	public Frog frog;
	GameManager GM;
	public ScoreHandler SH;
	Scene back;
	AnimationTimer timer;

	/**
	 * this constructor instantiates all objects for the scenes 
	 * @param PrimaryStage  is the base scene that is used to change what is displayed
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
	 * create the menu scene
	 * 
	 */
	private Scene CreatMenu() {

		Label label1 = new Label("Welcome to FROGGER");
		label1.setFont(new Font("Arial", 32));
		
	
		Button button1 = new Button("PLAY!");
		button1.setOnAction(e -> primaryStage.setScene(creategamescene()));
		button1.setMinSize(150, 70);
		button1.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");
		

		Button button2 = new Button("Exit ");
		button2.setOnAction(e -> primaryStage.close());
		button2.setMinSize(150, 70);
		button2.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");

		Button button3 = new Button("Info");
		button3.setOnAction(e -> primaryStage.setScene(infoscene));
		button3.setMinSize(150, 70);
		button3.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");

		Button button4 = new Button("Scoreboard");
		button4.setOnAction(e -> primaryStage.setScene(createtable()));
		button4.setMinSize(150, 70);
		button4.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");
		
		
		VBox layout1 = new VBox();
		layout1.setSpacing(50);
		layout1.setAlignment(Pos.CENTER);// Changed the alignment to center-left
		layout1.getChildren().addAll(label1, button1, button3, button4, button2);
		layout1.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene menuscene = new Scene(layout1, xres, yres);
		back = menuscene;
		return menuscene;
	}

	/**
	 *  create an info screen scene
	 * 
	 */
	private Scene CreateInfo() {
		Button button4 = new Button("Back");
		button4.setOnAction(e -> primaryStage.setScene(menuscene));
		button4.setMinSize(100, 50);
		button4.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");

		Label label1 = new Label("Get to the end!!!!!!!");
		label1.setFont(new Font("Arial", 32));

		Label label2 = new Label("Move with WASD and");
		label2.setFont(new Font("Arial", 32));
		
		Image background=new Image("file:src/resources/arcade.png", 600, 300, true, false);
		ImageView iv1 = new ImageView();
        iv1.setImage(background);


		VBox layout2 = new VBox();
		layout2.getChildren().addAll(iv1,label2, label1, button4);
		layout2.setSpacing(50);
		layout2.setBackground(new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		layout2.setAlignment(Pos.CENTER);
		return (new Scene(layout2, xres, yres));
	}

	/**
	 *  creates the game scene and starts everything needed for the game to run
	 * 
	 */
	Scene creategamescene() {
		int xres = 500;
		int yres = 800;
		background = new MyStage(); 
		Scene scene = new Scene(background, xres, yres);
		background.start();
		objects.addobjects(background);
		GM = new GameManager(this);
		frog = GM.frog;
		return (scene);
	}

	/**
	 *  creates a table that shows the highest 10 scores 
	 * 
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
		button4.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");

		VBox layout2 = new VBox();
		layout2.getChildren().addAll(table, button4);
		layout2.setSpacing(50);
		layout2.setBackground(
				new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		layout2.setAlignment(Pos.CENTER);// Changed the alignment to center-left
		return (new Scene(layout2, xres, yres));

	}

	/**
	 *  creates the win screen for the player 
	 */
	public void createwinscreen() {

		Label label1 = new Label("YOU WIN!!.");
		label1.setFont(new Font("Arial", 32));

		Label label2 = new Label("Score: " + frog.getPoints());
		label1.setFont(new Font("Arial", 32));

		Button button4 = new Button("Scoreboard");
		button4.setOnAction(e -> primaryStage.setScene(createtable()));
		button4.setMinSize(150, 70);
		button4.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");

		Button button2 = new Button("Exit");
		button2.setOnAction(e -> primaryStage.close());
		button2.setMinSize(150, 70);
		button2.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");

		TextField textField = new TextField();
		textField.setMaxSize(150, 70);

		Button button3 = new Button("Submit username");
		button3.setMinSize(150, 70);
		button3.setStyle("-fx-text-fill: #00ff00;-fx-background-color:black");

		button3.setOnAction(action -> {
			SH.writescores(textField.getText(), frog.getPoints(), background.lvl);
		});

		VBox layout1 = new VBox();
		layout1.setSpacing(50);
		layout1.setAlignment(Pos.CENTER);// Changed the alignment to center-left
		layout1.getChildren().addAll(label1, label2, textField, button3, button4, button2);
		layout1.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene winscene = new Scene(layout1, xres, yres);
		back = winscene;
		primaryStage.setScene(winscene);
	}

}
