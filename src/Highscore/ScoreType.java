package Highscore;

/**
 * This class is the data type for player score
 *
 */
public class ScoreType {

	public String Username;
	public int Score;
	public int Level;

	/**
     * The constructor
     * @param username is the user name of the player
	 * @param score is the score the player has have
	 * @param level is the level the player has reached
     */
	public ScoreType(String Username, int score, int Level) {

		this.Username = Username;
		this.Score = score;
		this.Level = Level;

	}

	public int getScore() {
		return (Score);
	}

	public int getLevel() {
		return (Level);
	}

	public String getUsername() {
		return (Username);
	}

	public void setScore(int Score) {
		this.Score = Score;
	}

	public void setLevel(int Level) {
		this.Level = Level;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

}
