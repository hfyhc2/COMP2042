package World;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * This class
 * 
 * @author khaled
 *
 */
public class MyStage extends World {
	MediaPlayer mediaPlayer;
	static public int lvl = 0;

	public MyStage() {

	}

	public void playMusic() {
		String musicFile = "src/resources/Frogger Main Song Theme (loop).mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	public void stopMusic() {
		mediaPlayer.stop();
	}

}