package Actor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import Actor.Actor;
import World.World;

public abstract class Actor extends ImageView {

	protected String type;
	protected ActorInterface actor;
	protected double speed;

	protected Image image1;
	protected Image image2;
	protected Image image3;
	protected Image image4;

	protected boolean sunk = false;
	public static int points;
	protected boolean carDeath, waterDeath;
	protected double movement;

	protected int imgSize;

	/**
	 * This method is used to move the object a number of pixels in the x direction
	 * and a number of pixels in the y direction
	 * 
	 * @param dx how many pixels to move in the x direction
	 * @param dy how many pixels to move in the y direction
	 */
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	/**
	 * fetches the parent object, which is the stage
	 * 
	 * @return the parent node, the root node
	 */
	public World getWorld() {
		return (World) getParent();
	}

	/**
	 * Method to check intersection between objects
	 * 
	 * @param <A> an empty arraylist
	 * @param cls The object to check intersections with
	 * @return an array containing all actors that are intersecting
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

	/**
	 * types of actors such as turtle or log, used for placement functions
	 * 
	 * @return the type of actor
	 * @see World#checknextfree(String)
	 */
	public String getType() {
		return type;
	}

	/**
	 * A call to the encapsulated method act
	 * 
	 * @param now game timer
	 */
	public void actor(long now) {
		actor.actor(this, now);

	}

	/**
	 * method for animal
	 * 
	 * @param now
	 */
	public void handledeath(long now) {

	}

}
