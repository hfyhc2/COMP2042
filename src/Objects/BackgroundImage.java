package Objects;

import Actor.*;
import javafx.scene.image.Image;

public class BackgroundImage extends Actor {

	@Override
	public void actor(long now) {

	}

	public BackgroundImage(String imageLink) {
		actor = new ActorIdle();
		setImage(new Image(imageLink, 600, 900, true, true));

	}

}
