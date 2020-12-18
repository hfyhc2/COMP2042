package World;


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
 * A class for the game, it handles all the object interactions with the game
 *
 */
public abstract class World extends Pane {
	private AnimationTimer timer;
	static String[] positionslots = new String[] { "", "", "", "", "", "", "", "", "", "", "" };
	static int[] positionfull = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };

	 /**
     *   define input output functions whenever the scene changes 
     */
	public World() {

		sceneProperty().addListener(new ChangeListener<Scene>() {

			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {

					
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
     * this function starts the timer and calls the functionality of each actor
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
     * starts the timer
     */
	public void start() {
		createTimer();
		timer.start();
	}

	/**
     * stops the timer
     */
	public void stop() {
		timer.stop();
	}
	
	/**
     * this function adds an actor as a child to the outer most layer
     * @param actor is the actor to be added to the stage
     */
	public void addfront(Actor actor) {

		getChildren().add(getChildren().size(), actor);
	}

	
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
     * this function removes actor from the root node
     * @param actor is the actor to be deleted from the stage
     */
	
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	/**
     * resets all stage data to start a new game 
     */
	public void removeall() {
		getChildren().removeAll();
		positionslots = new String[] { "", "", "", "", "", "", "", "", "", "", "" };
		positionfull = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
		
	}
	
	/**
     * Searches through root children for specified node
     * @param <A> is an empty array
     * @param cls is the node to be searched for
     * @return a is the list of the objects of that node
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
	   * checks the next slot on the Y axis to add another obstacle 
	   * @param is the type of object to be added 
	   * @return is the y position the object should be placed in
	   */
	public coordinate checknextfree(String type) {
		 Random rand = new Random(); 
		 coordinate pos=new coordinate(rand.nextInt(150),710);
		int j=0;
		if (type.contains("Turtle") || type.contains("Log")) {
			j=6;
		}
		else {j=0;}
		for ( int i=j; i < positionfull.length; i++) {
			if(i==5) {
				return null;
			}
			
			pos.y=710-50*(i+1);
			
			if (positionfull[i]>0) {
				if (positionslots[i]=="") {
					positionslots[i]=type;
					positionfull[i]--;
					return(pos);
				}
				else {
					if (positionslots[i].contentEquals(type)) {
						pos.x=pos.x+300*(3-positionfull[i]);
						positionfull[i]--;
	    				return(pos);
					}
					else {
					}
				}
			}
		}
		return(null);
	}


	}
