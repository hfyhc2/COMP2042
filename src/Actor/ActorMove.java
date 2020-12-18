package Actor;

public class ActorMove implements ActorInterface {
	@Override
	public void actor(Actor actor, long now) {
		actor.move(actor.speed, 0);
		restrictmovement(actor);

	}

	@Override
	public void restrictmovement(Actor actor) {
		if (actor.getX() > 600 && actor.speed > 0)
			actor.setX(-200);
		if (actor.getX() < -100 && actor.speed < 0)
			actor.setX(800);
	}

}
