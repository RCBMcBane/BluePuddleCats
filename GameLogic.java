

/**
 * @author BluePuddleCat
 * @version 11/2/18
 * Written for HackHolyoke 2018
 *
 */
public class GameLogic {
	
	//global variables
	private WordLibrary library;
	private Word word;
	private int score;
	
	/**
	 * constructor initiates new instance of WordLibrary, sets score to 0.
	 */
	public GameLogic() {
		library = new WordLibrary();
		score = 0;
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
	 * @return new word from WordLibrary
	 */
	public Word updateWord() {
		word = library.getRandomWord();
		return word;
	}
	

	/**
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	
	/**
	 * compare given category with the original category (string comparison, use “.equals”)
	 * if the same, correct is true, update score by 1
	 * if not, correct is false, do nothing
	 * @param Category the category the user chose in the game for the word
	 * @return whether that word belongs in the category or not
	 */
	public boolean compare(String Category) {
		String category = word.getCategory();
		boolean correct = category.equals( Category );	
		if( correct ) {
			score ++;
		} 
		return correct;
	}

}
