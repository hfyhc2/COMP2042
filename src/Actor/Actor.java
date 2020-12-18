package Actor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import Actor.Actor;
import World.World;
/**
 * This abstract class is used to define all objects in the game
 *
 */
public abstract class Actor extends ImageView {
	/**
	 * Type stores the type of object to be added, is used for the slot system in class world
	 */
	protected String type;
	/**
	 * The functionality of each object
	 */
	protected ActorInterface actor;
	/**
	 * the speed for moving objects
	 */
	protected double speed;
	/**
	 * movement animations and death animations
	 */
	protected Image image1;
	protected Image image2;
	protected Image image3;
	protected Image image4;
	/**
	 * Frog's variables
	 */
	protected boolean sunk = false;
	public static int points;
	protected boolean carDeath, waterDeath;
	protected double movement;

	protected int imgSize;
	/**
	 * this method moves the object in the x and y axis 
	 * @param dx amount of distance to be moved on the x axis
	 * @param dy amount of distance to moved on the y axis
	 */
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	
	public World getWorld() {
		return (World) getParent();
	}

	/**
     * this method checks the intersection between objects
     * @param <A> type of class to check for an interaction
	 * @param cls object of class to check for interaction
	 * @return returns an array containing objects intersected with
     */
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		ArrayList<A> someArray = new ArrayList<A>();
		for (A actor : getWorld().getObjects(cls)) {
			if (actor != this && actor.intersects(this.getBoundsInLocal())) {
				someArray.add(actor);
			}
		}
		return someArray;
	}
	
	
	public String getType() {
		return type;
	}

	public void actor(long now) {
		actor.actor(this, now);

	}

	public void handledeath(long now) {

	}

}
