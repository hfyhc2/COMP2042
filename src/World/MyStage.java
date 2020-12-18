package World;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * this class plays the music
 *
 */
public class MyStage extends World {
	MediaPlayer mediaPlayer;
	static public int lvl = 0;

	public MyStage() {

	}
	/**
	 * plays the music
	 *
	 */
	public void playMusic() {
		String musicFile = "src/resources/Frogger Main Song Theme (loop).mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	/**
	 * stops the music
	 *
	 */
	public void stopMusic() {
		mediaPlayer.stop();
	}

}
