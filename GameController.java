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
	private boolean gameStart;
	private JButton catgry1, catgry2, catgry3;
	private boolean correct;

	/**
	 * constructor
	 */
	public GameController() {
		super(new BorderLayout());
		setFocusable(true);
		logic = new GameLogic();
		logic.updateWord();
		gameStart = false;
		createView();
	}

	/**
	 * createView method creates all the elements that need to go into the frame for
	 * the game to run.
	 */
	private void createView() {
		displayCategoryScore();
		displayWord();
		// display instruction
		displayComment(gameStart);
	}

	/**
	 * createCategoryView method creates the three buttons that are the possible
	 * categories for the word
	 */
	private void displayCategoryScore() {
		JPanel categoryScorePane = new JPanel(new GridLayout(2, 1));
		//create category view
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
		//create score view
		String score = "Score: " + String.valueOf(logic.getScore());
		JLabel scoreLabel = new JLabel(score, SwingConstants.CENTER);
		add(categoryScorePane, BorderLayout.NORTH);
		categoryScorePane.add(catPanel);
		categoryScorePane.add(scoreLabel);
		revalidate();
	}

	/**
	 * this method displays the new word for the user to guess
	 */
	private void displayWord() {
		JPanel wordPane = new JPanel(new BorderLayout());
		JLabel wordLabel = new JLabel(logic.getWord().getData(), SwingConstants.CENTER);
		wordPane.add(wordLabel,BorderLayout.CENTER);
		add(wordPane, BorderLayout.CENTER);
		revalidate();
	}

	/**
	 * @param wasCorrect,
	 *            boolean, true if the answer was correct this method displays the
	 *            comments after the user has guessed they are different if the used
	 *            got the answer right or wrong
	 */
	private void displayComment(boolean gameOn) {
		JPanel commentPane = new JPanel(new BorderLayout());
		if (gameOn) {
			//after user has clicked
			//display comment
			String comment = getComment(correct);
			JLabel commentLabel = new JLabel(comment, SwingConstants.CENTER);
			commentPane.add(commentLabel, BorderLayout.CENTER);
		} else {
			//at the beginning on the game, before any clicks
			//display instructions
			String instructions = "<html>How to Play: <br/> A word will appear on the screen.<br/>You must decide if it is a noun, a verb, "
					+ "or an adjective.<br/>When you have decided, click the corresponding button at the top.<br/>"
					+ "If you get it right, you will get a point!<br/>Once you have clicked, a new word will appear."
					+ "<br/>Try to get through all the words with the highest score possible!</html>";
			JLabel instructionLabel = new JLabel(instructions, SwingConstants.CENTER);
			commentPane.add(instructionLabel, BorderLayout.CENTER);
		}
		add(commentPane, BorderLayout.SOUTH);
		revalidate();
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
	 * this method performs the main game play of the Language Game
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		String text = ((AbstractButton) e.getSource()).getText();
		// compare selected category with the original
		correct = logic.compare(text);
		logic.updateWord();
		gameStart = true;
		// refresh display
		createView();
	}
