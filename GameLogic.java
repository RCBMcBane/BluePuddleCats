/**
 * Creates an instance of WordLibrary and generates random words from it. The
 * category the user chooses in the game is compared to the category belonging
 * to the word, and whether the user's choice is correct or not is returned. If
 * the user's choice is correct, the score is updated.
 * 
 * @author BluePuddleCat
 * @version 11/2/18 Written for HackHolyoke 2018
 *
 */
public class GameLogic {

	// global variables
	private Library library;
	private Word word;
	private int score;
	private int timesAttempted;

	/**
	 * constructor initiates new instance of WordLibrary, sets score to 0.
	 */
	public GameLogic(String version) {
		library = new Library(version);
		score = 0;
		timesAttempted = 0;
	}

	/**
	 * 
	 * @return existing word
	 */
	public Word getWord() {
		return word;
	}

	/**
	 * gets random word from WordLibrary
	 * 
	 * @return new word from WordLibrary
	 */
	public Word updateWord() {
		word = library.getRandomWord();
		return word;
	}

	/**
	 * get score
	 * @return score
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
	 * get attempted times
	 * @return attempted times
	 */
	public int getTimesAttempted() {
		return timesAttempted;
	}

	/**
	 * compare given category with the original category
	 * 
	 * @param Category
	 *            the category the user chose in the game for the word
	 * @return whether that word belongs in the category or not
	 */
	public boolean compare(String Category) {
		String category = word.getCategory();
		boolean correct = category.equals(Category);
		if (correct)
			score++;
		timesAttempted++;
		return correct;
	}

}
