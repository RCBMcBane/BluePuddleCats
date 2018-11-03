import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
/**
 * 
 * @author BluePuddleCats
 * @version 11/2/18
 * This creates a GameController object
 * which is in control of Learning Game
 *
 */
public class GameController extends JPanel implements ActionListener{
	boolean gameHasBegun = false;
	GameLogic logic;
	String comment;
	JButton catgry1 = new JButton("Noun");
	JButton catgry2 = new JButton("Verb");
	JButton catgry3 = new JButton("Adjective");
	
	/**
	 * constructor
	 */
	public GameController() {
		//constructor for game controller
	}
	/**
	 * createView method creates all the elements
	 * that need to go into the frame for the game to run.
	 */
	public void createView() {
		// use a border layout
		super(new BorderLayout()); //this gives me an error in Eclipse 
		//"constructor call must be the first statement in a constructor"
		
		createCategoryView();
		createScoreView();
		displayWord();
		displayInstructions();
		}
	/**
	 * this method refreshes the display after the user had performed some action
	 * @param wasCorrect boolean, true if user's answer was correct
	 */
	public void refreshDisplay(boolean wasCorrect) {
		//update word and display
		displayWord();
		createCategoryView();
		//update score
		createScoreView();
		//update comment
		createCommentView(wasCorrect);
	}
	/**
	 * createCategoryView method creates the three buttons
	 * that are the possible categories for the word
	 */
	public void createCategoryView() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel topPanel = new JPanel(new GridLayout(1, 3));
		topPanel.add(catgry1);
		topPanel.add(catgry2);
		topPanel.add(catgry3);
		this.add(topPanel, BorderLayout.NORTH); //don't know if this is right	
	}
	/**
	 * this method adds the score to the screen
	 */
	//THIS METHOD NEEDS HELP, I DON'T KNOW WHAT TO DO WITH JALABEL
	public void createScoreView() {
		String score = String.valueOf(logic.getScore());
		JLabel scoreView = new JLabel(score);
	}
	/**
	 * this method displays the new word for the user to guess
	 */
	//THIS METHOD NEEDS HELP, I DON'T KNOW WHAT TO DO WITH JALABEL
	public void displayWord() {
		String word = logic.getword();
		JLabel printWord = new JLabel(word);
	}
	/**
	 * @param wasCorrect, boolean, true if the answer was correct
	 * this method displays the comments after the user has guessed
	 * they are different if the used got the answer right or wrong
	 */
	//THIS METHOD NEEDS HELP, I DON'T KNOW WHAT TO DO WITH JALABEL
	public void createCommentView(boolean wasCorrect) {
		String comment = getComment(wasCorrect);
		JLabel printComment = new JLabel(comment);
	}
	/**
	 * @param wasCorrect, boolean, true if the user's guess was correct
	 * returns a comment based on whether the user answer was correct or incorrect
	 */
	public String getComment(boolean wasCorrect) {
		if(wasCorrect) {
			return "Great Job! You got it right!";
		} else {
			return "Good try, but that is not the right answer.";
		}
	}
	/**
	 * This method displays instructions for playing the partOfSpeech game.
	 */
	public void displayInstructions() {
		String instructions =  "A word will appear on the screen.\nYou must decide if it is a noun, a verb, "
				+ "or an adjective.\nWhen you have decided, click the corresponding button"
				+ " at the top.\nIf you get it right, you will get a point!\nOnce you have clicked,"
				+ " a new word will appear.\nTry to get through all the words with the highest "
				+ "score possible!"; 
		JLabel printInstructions = new JLabel(instructions);
	}
	/**
	 * this method performs the main game play of the Language Game
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		boolean wasCorrect; //if the answer was correct
		String category; //the answer that the user chose
		//compare selected category with the original
		wasCorrect = logic.compare(category);
		//(do we need to) update score, etc.?
		//refresh the display
		refreshDisplay(wasCorrect);
	}
}
