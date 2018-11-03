/**
 * Creates an instance of WordLibrary and generates random words from it. The
 * category the user chooses in the game is compared to the category belonging
 * to the word, and whether the user's choice is correct or not is returned. If
 * the user's choice is correct, the score is updated.
 * 
 * @author BluePuddleCat
 * @version 11/3/18 Written for HackHolyoke 2018
 *
 */
public class GameLogic {

	// instance variables
	private Library library;
	private Word word;
	private int score;
	private int timesAttempted;

	/**
	 * @param version String, which version of the game is being played
	 * constructor initiates new instance of WordLibrary, 
	 * sets score and times attemptedto 0.
	 */
	public GameLogic(String version) {
		library = new Library(version);
		score = 0;
		timesAttempted = 0;
	}

	/**
	 * getter method for word
	 * @return existing word, Word object
	 */
	public Word getWord() {
		return word;
	}

	/**
	 * gets random word from WordLibrary
	 * @return new word from WordLibrary, Word object
	 */
	public Word updateWord() {
		word = library.getRandomWord();
		return word;
	}

	/**
	 * getter method for score
	 * @return score, int
	 */
	public int getScore() {
		return score;
	}

	/**
	 * reset score to 0
	 */
	public void resetScore() {
		score = 0;
	}

	/**
	 * getter method for attempted times
	 * @return attempted times, int
	 */
	public int getTimesAttempted() {
		return timesAttempted;
	}

	/**
	 * compare guessed category with the correct category of the word
	 * 
	 * @param guessedCategory, String, the category the user chose in the game
	 * @return boolean, true is guess was correct
	 */
	public boolean compare(String guessedCategory) {
		String category = word.getCategory();
		boolean correct = category.equals(guessedCategory);
		if (correct)
			score++;
		timesAttempted++;
		return correct;
	}

}
