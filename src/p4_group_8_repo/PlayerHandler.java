package p4_group_8_repo;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *  responsible for handling player's input output 
 *
 */
public class PlayerHandler {

	// movement animation images
	Image imgW1,imgA1,imgS1,imgD1,imgW2,imgA2,imgS2,imgD2;
	Image imgw,imga,imgs,imgd;
	int imgSize = 40;
	private boolean second = false; // used to swap between moving animtion, can be handelled better
	double movement = 50;
	double movementX = 50;
	Frog frog;

	/**
	 * determine the image required for player movement
	 * @param frog is a passed reference of the player object
	 */
	public PlayerHandler(Frog frog) {
		this.frog = frog;

		imgW1 = new Image("file:src/resources/froggerUp.png", imgSize, imgSize, true, true);
		imgA1 = new Image("file:src/resources/froggerLeft.png", imgSize, imgSize, true, true);
		imgS1 = new Image("file:src/resources/froggerDown.png", imgSize, imgSize, true, true);
		imgD1 = new Image("file:src/resources/froggerRight.png", imgSize, imgSize, true, true);
		imgW2 = new Image("file:src/resources/froggerUpJump.png", imgSize, imgSize, true, true);
		imgA2 = new Image("file:src/resources/froggerLeftJump.png", imgSize, imgSize, true, true);
		imgS2 = new Image("file:src/resources/froggerDownJump.png", imgSize, imgSize, true, true);
		imgD2 = new Image("file:src/resources/froggerRightJump.png", imgSize, imgSize, true, true);

	}

	/**
	 * checks keyboard input and does the actions once input is detected
	 * 
	 */
	public void checkcontrol() {
		frog.checkIntersections();

		frog.setOnKeyPressed(new EventHandler<KeyEvent>() { 
			public void handle(KeyEvent event) {
				if (frog.noMove) {
					
				} else {
					if (second) {
						imgw = imgW1;
						imga = imgA1;
						imgs = imgS1;
						imgd = imgD1;

					} else {
						imgw = imgW2;
						imga = imgA2;
						imgs = imgS2;
						imgd = imgD2;
					}

					if (event.getCode() == KeyCode.W) {
						frog.move(0, -movement);
						frog.setImage(imgw);
						second = false;
					} else if (event.getCode() == KeyCode.A) {
						frog.move(-movementX , 0);
						frog.setImage(imga);
						second = false;
					} else if (event.getCode() == KeyCode.S) {
						frog.move(0, movement);
						frog.setImage(imgs);
						second = false;
					} else if (event.getCode() == KeyCode.D) {
						frog.move(movementX , 0);
						frog.setImage(imgd);
						second = false;
					}
				}
			}
		});
		frog.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (frog.noMove) {
				} else {
					if (event.getCode() == KeyCode.W) {
						if (frog.getY() < frog.w) {
							frog.changeScore = true;

							frog.w = frog.getY();
							frog.points += 10;
						}
						frog.setImage(imgW1);
						second = false;
					} else if (event.getCode() == KeyCode.A) {
						frog.setImage(imgA1);
						second = false;
					} else if (event.getCode() == KeyCode.S) {
						frog.setImage(imgS1);
						second = false;
					} else if (event.getCode() == KeyCode.D) {
						frog.setImage(imgD1);
						second = false;
					}
				}
			}

		});
	}

}
