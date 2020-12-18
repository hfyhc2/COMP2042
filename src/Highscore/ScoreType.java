package Highscore;

/**
 * This class is a datatype used for storing the player score
 * 
 * @author khaled
 *
 */
public class ScoreType {

	public String Username;
	public int Score;
	public int Level;

	/**
	 * The constructor
	 * 
	 * @param Username the username the player wants to use
	 * @param score    the score the player has accumulated
	 * @param Level    the level the player has reached
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
