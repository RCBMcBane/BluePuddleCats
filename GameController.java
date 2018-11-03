import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

/**
 * GameController class initiates game and display in GUI
 * 
 * @author BluePuddleCats
 * @version 11/2/18
 *
 */
public class GameController extends JPanel implements ActionListener {
	private GameLogic logic;
	private JButton catgry1, catgry2, catgry3;
	private boolean correct;

	/**
	 * constructor
	 */
	public GameController() {
		super(new GridLayout(3, 1));
		setFocusable(true);
		logic = new GameLogic();
		logic.updateWord();
		createView();
	}

	/**
	 * createView method creates all the elements that need to go into the frame for
	 * the game to run.
	 */
	public void createView() {
		// display category and score
		JPanel categoryScorePane = new JPanel(new GridLayout(2, 1));
		add(categoryScorePane);
		categoryScorePane.add(createCategoryView());
		categoryScorePane.add(createScoreView());
		// display word
		add(displayWord());
		// display instruction
		add(displayInstructions());
	}

	/**
	 * this method refreshes the display after the user had performed some action
	 * 
	 * @param wasCorrect
	 *            boolean, true if user's answer was correct
	 */
	public void refreshDisplay() {
		// update word and display
		displayWord();
		// update score
		createScoreView();
		// update comment
		createCommentView();
	}

	/**
	 * createCategoryView method creates the three buttons that are the possible
	 * categories for the word
	 */
	private JPanel createCategoryView() {
		JPanel catPanel = new JPanel(new GridLayout(1, 3));
		catgry1 = new JButton("Noun");
		catgry1.addActionListener(this);
		catgry2 = new JButton("Verb");
		catgry2.addActionListener(this);
		catgry3 = new JButton("Adjective");
		catgry3.addActionListener(this);
		catPanel.add(catgry1);
		catPanel.add(catgry2);
		catPanel.add(catgry3);
		revalidate();
		return catPanel;
	}

	/**
	 * this method create score label
	 */
	private JLabel createScoreView() {
		String score = "Score: " +String.valueOf(logic.getScore());
		JLabel scoreLabel = new JLabel(score, SwingConstants.CENTER);
		revalidate();
		return scoreLabel;
	}

	/**
	 * this method displays the new word for the user to guess
	 */
	private JLabel displayWord() {
		JLabel wordLabel = new JLabel(logic.getWord().getData(), SwingConstants.CENTER);
		revalidate();
		return wordLabel;
	}

	/**
	 * @param wasCorrect,
	 *            boolean, true if the answer was correct this method displays the
	 *            comments after the user has guessed they are different if the used
	 *            got the answer right or wrong
	 */
	private JLabel createCommentView() {
		String comment = getComment(correct);
		JLabel commentLabel = new JLabel(comment, SwingConstants.CENTER);
		revalidate();
		return commentLabel;
	}

	/**
	 * @param wasCorrect,
	 *            boolean, true if the user's guess was correct returns a comment
	 *            based on whether the user answer was correct or incorrect
	 */
	private String getComment(boolean right) {
		if (right) {
			return "Great Job! You got it right!";
		} else {
			return "Good try, but that is not the right answer.";
		}
	}

	/**
	 * This method displays instructions for playing the partOfSpeech game.
	 */
	private JLabel displayInstructions() {
		String instructions = "A word will appear on the screen.\nYou must decide if it is a noun, a verb, "
				+ "or an adjective.\nWhen you have decided, click the corresponding button"
				+ " at the top.\nIf you get it right, you will get a point!\nOnce you have clicked,"
				+ " a new word will appear.\nTry to get through all the words with the highest " + "score possible!";
		JLabel instructionLabel = new JLabel(instructions);
		return instructionLabel;
	}

	/**
	 * this method performs the main game play of the Language Game
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		String text = ((AbstractButton) e.getSource()).getText();
		// compare selected category with the original
		correct = logic.compare(text);
		//refresh display
		refreshDisplay();
	}
}
