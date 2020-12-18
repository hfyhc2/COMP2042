package Actor;

/**
 * abstract class for functionality
 *
 */

public interface ActorInterface {

	/**
	 * abstract method for actor functionality when in game time
	 * @param actor is the object that needs functionality and now is for game time
	 */
	
	public void actor(Actor actor, long now);

	/**
	 * This method stops actor from going off screen
	 * @param actor is the object that is restricted
	 */
	public void restrictmovement(Actor actor);
}
