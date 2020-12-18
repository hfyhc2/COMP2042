package Highscore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import p4_group_8_repo.Frog;

public class ScoreHandler {

	public ArrayList<ScoreType> scores;
	public ArrayList names;

	int score = 0;

	Frog frog;

	public File file;

	/**
	 * The constructor checks for the text file , and creates one if it doesnt
	 * exist, it also calls the readscores function to initialise the scoreboard
	 */
	public ScoreHandler() {

		scores = new ArrayList<ScoreType>();
		ArrayList<String> names = new ArrayList<String>();

		File file = new File("Highscore.txt");
		try {
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		readscores();

	}

	/**
	 * This function writes new data into the file, it works by first adding the new
	 * score to the scores list. and then writing all entities of the list to the
	 * file in the correct format
	 * 
	 * @param username the username the player wants to use
	 * @param score    the score the player has accumulated
	 * @param level    the level the player has reached it also checks for an
	 *                 existing username, and if it exists and is lower than the new
	 *                 score then it replaces the lower score with the higher score
	 */
	public void writescores(String username, int score, int level) {
		boolean found = false;

		for (int x = 0; x < scores.size(); x++) {
			if (scores.get(x).Username.equals(username)) {
				if (scores.get(x).Score < score) {
					scores.get(x).Score = score;
					scores.get(x).Level = level;
				}
				found = true;
			}
		}
		if (!found) {
			scores.add(new ScoreType(username, score, level));
		}

		try {
			FileWriter myWriter = new FileWriter("Highscore.txt");
			for (int i = 0; i < scores.size(); i++) {

				if (!scores.get(i).Username.equals("")) {
					myWriter.write(
							scores.get(i).Username + "\n" + scores.get(i).Score + "\n" + scores.get(i).Level + "\n");
				}
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	/**
	 * This function reads the score data from a text file and stores in in an array
	 */
	public void readscores() {
		int second = 1;
		String user = "";
		int score = 0;
		try {
			File myObj = new File("Highscore.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (second == 3) {

					scores.add(new ScoreType(user, score, Integer.valueOf(data)));
					second = 1;
				} else if (second == 1) {

					second = 2;
					user = data;
				} else if (second == 2) {
					second = 3;
					score = Integer.valueOf(data);
				}

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * A public method to get the scores
	 * 
	 * @return an array containing the player scores
	 */
	public ArrayList getscore() {
		return (scores);
	}

	/**
	 * A function that gets the hiscore
	 * 
	 * @return the highest score
	 */
	public int gethiscore() {
		int hi = 0;
		for (int i = 0; i < scores.size(); i++) {
			if (scores.get(i).Score > hi) {
				hi = scores.get(i).Score;
			}
		}
		return (hi);
	}

}
