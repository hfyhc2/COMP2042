package p4_group_8_repo;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class is responsible for handling i/o of the player
 * 
 * @author khaled
 *
 */
public class PlayerHandler {

	// movement animation images
	Image imgW1;
	Image imgA1;
	Image imgS1;
	Image imgD1;
	Image imgW2;
	Image imgA2;
	Image imgS2;
	Image imgD2;

	Image imgw;
	Image imga;
	Image imgs;
	Image imgd;

	int imgSize = 40;

	private boolean second = false; // used to swap between moving animtion, can be handelled better
	double movement = 25;
	double movementX = 22;

	Frog frog;

	/**
	 * This method sets up the player model by defining all sprites required for
	 * movement
	 * 
	 * @param animal This is a passed reference of the player object
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
	 * This method runs with the game timer and its in charge for checking keyboard
	 * input and arranging appropriate actions once input is detected ands it
	 * changes animal sprites accordingly since it is a constantly running function
	 * it also calls the checkintersections function
	 * 
	 * @see Animal#checkIntersections()
	 */
	public void checkcontrol() {
		frog.checkIntersections();

		// System.out.println(points);
		frog.setOnKeyPressed(new EventHandler<KeyEvent>() { // when key is first pressed
			public void handle(KeyEvent event) {
				if (frog.noMove) {
					// do nothing
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
						frog.move(0, -movement * 2);
						frog.setImage(imgw);
						second = !second;
					} else if (event.getCode() == KeyCode.A) {
						frog.move(-movementX * 2, 0);
						frog.setImage(imga);
						second = !second;
					} else if (event.getCode() == KeyCode.S) {
						frog.move(0, movement * 2);
						frog.setImage(imgs);
						second = !second;
					} else if (event.getCode() == KeyCode.D) {
						frog.move(movementX * 2, 0);
						frog.setImage(imgd);
						second = !second;
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
