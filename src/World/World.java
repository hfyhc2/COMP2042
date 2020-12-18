package World;

//commented
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Actor.Actor;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * A class defenition for the game scene, it handles all object interactions
 * with the world
 * 
 * @author khalef
 *
 */
public abstract class World extends Pane {
	private AnimationTimer timer;

	static String[] positionslots = new String[] { "", "", "", "", "", "", "", "", "", "", "" };
	static int[] positionfull = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
	static int[] positionxaxis = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	/**
	 * This constructor is set to define key i/o functions whenever a scene change
	 * is detected
	 */
	public World() {

		sceneProperty().addListener(new ChangeListener<Scene>() {

			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {

					/**
					 * This function handles io and is overridden in PlayerController
					 */
					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor : myActors) {
								if (anActor.getOnKeyReleased() != null) {
									anActor.getOnKeyReleased().handle(event);
								}
							}
						}

					});

					/**
					 * Another function to handle key i/o thats overridden in playercontroller
					 */
					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor : myActors) {
								if (anActor.getOnKeyPressed() != null) {
									anActor.getOnKeyPressed().handle(event);
								}
							}
						}

					});
				}

			}

		});
	}

	/**
	 * This function starts a game timer and calls the act functionality of each
	 * actor
	 */
	public void createTimer() {
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				List<Actor> actors = getObjects(Actor.class);

				for (Actor anActor : actors) {
					anActor.actor(now);
				}

			}
		};
	}

	/**
	 * this is where this script starts, and it calls to initialise and start the
	 * game timer
	 */
	public void start() {
		createTimer();
		timer.start();
	}

	/**
	 * Function to stop game timer ending the game
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * function to add an actor as a child to the front of the pane, the outer most
	 * layer
	 * 
	 * @param actor the actor to be added to the stage
	 */
	public void addfront(Actor actor) {

		getChildren().add(getChildren().size(), actor);
	}

	/**
	 * This function is used to add objects to the stage at the lowest layer, after
	 * adding the object, it calls the checknextfree to see where the object should
	 * be placed if its of the type that requires it
	 * 
	 * @param actor the object to be added as a child node
	 */
	public void add(Actor actor) {
		getChildren().add(0, actor);
		if (actor.getType() != null) {
			coordinate temp = checknextfree(actor.getType());
			if (temp != null) {
				actor.setY(temp.y);
				actor.setX(temp.x);
			} else {
				remove(actor);
			}
		}

	}

	/**
	 * Function to remove actor from the root node
	 * 
	 * @param actor the actor to be deleted from the stage
	 */
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	/**
	 * function to reset all stage data to start a new game scene, removes all
	 * children and resets slot system
	 */
	public void removeall() {
		getChildren().removeAll();
		positionslots = new String[] { "", "", "", "", "", "", "", "", "", "", "" };
		positionfull = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
		positionxaxis = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	}

	/**
	 * Searches through root children for specified node
	 * 
	 * @param <A> an empty array
	 * @param cls the node to be searched for
	 * @return a list of the objects of that node
	 */
	public <A extends Actor> List<A> getObjects(Class<A> cls) {
		ArrayList<A> someArray = new ArrayList<A>();
		for (Node n : getChildren()) {
			if (cls.isInstance(n)) {
				someArray.add((A) n);
			}
		}
		return someArray;
	}

	/**
	 * method that implements slot system that checks the next slot on the Y axis
	 * that the obstacle can be added to
	 * 
	 * @param type the type of object to be added (e.g: turtle, log)
	 * @return the y position the object should be placed in
	 */
	public coordinate checknextfree(String type) {
		Random rand = new Random();
		coordinate pos = new coordinate(0, 710);
		int j = 0;
		if (type.contains("Turtle") || type.contains("Log")) {
			j = 6;
		} else {
			j = 0;
		}
		for (int i = j; i < positionfull.length; i++) {
			if (i == 5) {
				return null;
			}

			pos.y = 710 - 50 * (i + 1);

			if (positionslots[i] == "") {
				positionslots[i] = type;
				pos.x = rand.nextInt(140) + 10;// the x coordinate for the first item in a slot is random

				positionxaxis[i] = pos.x;
				if (type.contains("truck")) {
					positionfull[i] = positionfull[i] - 2;
				} else {
					positionfull[i]--;
				}
				return (pos);
			} else {
				if (positionfull[i] > 0) {
					if (positionslots[i].contentEquals(type)) {
						pos.x = positionxaxis[i] + (rand.nextInt(70) + 150); // the x coordinate is dependant on how
																				// many items are already in the slot
																				// and on a random factor with just
																				// enough space to avoid overlap
						positionxaxis[i] = pos.x;
						if (type.contains("truckbig")) {
							positionfull[i] = positionfull[i] - 2;
							pos.x = pos.x + 80;
						} else {
							positionfull[i]--;
						}
						return (pos);
					} else {
					}
				}
			}

		}
		return (null);
	}

}
