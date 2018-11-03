import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JFrame;

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
	private String version;
	private JButton catgry1, catgry2, catgry3;
	private boolean correct;

	/**
	 * constructor
	 */
	public GameController() {
		super(new BorderLayout());
		setFocusable(true);
		gameStart = false;
		displayVersion();
	}

	/**
	 * display two buttons for word and recycling game
	 */
	private void displayVersion() {
		JPanel versionPane = new JPanel(new GridLayout(1, 2));
		// create buttons
		JButton wordVersion = new JButton("Word Game");
		wordVersion.addActionListener(this);
		JButton recycleVersion = new JButton("Recycle Game");
		recycleVersion.addActionListener(this);
		// add them to panels
		versionPane.add(wordVersion);
		versionPane.add(recycleVersion);
		add(versionPane, BorderLayout.CENTER);
	}

	/**
	 * createView method creates all the elements that need to go into the frame for
	 * the game to run.
	 */
	private void createView() {
		displayCategoryScore();
		displayWordComment();
		displaySkipEnd();
	}

	/**
	 * createCategoryView method creates the three buttons that are the possible
	 * categories for the word
	 */
	private void displayCategoryScore() {
		Font font = new Font("Comic Sans MS", Font.BOLD, 30);
		
		JPanel categoryScorePane = new JPanel(new GridLayout(2, 1));
		// create category view
		JPanel catPanel = new JPanel(new GridLayout(1, 3));
		if (version.equals("Word Game")) {
			catgry1 = new JButton("Noun");
			catgry2 = new JButton("Verb");
			catgry3 = new JButton("Adjective");
		} else if (version.equals("Recycle Game")) {
			catgry1 = new JButton("Trash");
			catgry2 = new JButton("Recycle");
			catgry3 = new JButton("Compost");
		}
		catgry1.setBackground(new Color(225, 225, 180));
		catgry2.setBackground(new Color(255, 200, 200));
		catgry3.setBackground(new Color(135, 206, 250));
		catgry1.setFont(font);
		catgry2.setFont(font);
		catgry3.setFont(font);
		catgry1.addActionListener(this);
		catgry2.addActionListener(this);
		catgry3.addActionListener(this);
		catPanel.add(catgry1);
		catPanel.add(catgry2);
		catPanel.add(catgry3);
		// create score view
		String score = "Score: " + String.valueOf(logic.getScore());
		JLabel scoreLabel = new JLabel(score, SwingConstants.CENTER);
		scoreLabel.setFont(font);
		add(categoryScorePane, BorderLayout.NORTH);
		categoryScorePane.add(catPanel);
		categoryScorePane.add(scoreLabel);
		revalidate();
	}

	/**
	 * this method displays the new word for the user to guess and the comment
	 */
	private void displayWordComment() {
		// display word
		JPanel wordCommentPane = new JPanel(new GridLayout(2, 1));
		JLabel wordLabel = new JLabel(logic.getWord().getData(), SwingConstants.CENTER);
		wordCommentPane.add(wordLabel);

		// display comment or instructions
		JPanel commentPane = new JPanel(new BorderLayout());
		if (gameStart) {
			// after user has clicked
			// display comment
			String comment = "";
			if (correct) {
				comment = "Great Job! You got it right!";
			} else {
				comment = "Good try, but that is not the right answer.";
			}
			JLabel commentLabel = new JLabel(comment, SwingConstants.CENTER);
			commentPane.add(commentLabel, BorderLayout.CENTER);
		} else {
			// at the beginning on the game, before any clicks
			// display instructions
			String instructions = "<html>How to Play: <br/> A word will appear on the screen.<br/>You must decide which "
					+ "category it belongs to.<br/>When you have decided, click the corresponding button at the top.<br/>"
					+ "If you get it right, you will get a point!<br/>Once you have clicked, a new word will appear."
					+ "<br/>Try to get through all the words with the highest score possible!</html>";
			JLabel instructionLabel = new JLabel(instructions, SwingConstants.CENTER);
			commentPane.add(instructionLabel, BorderLayout.CENTER);
		}
		wordCommentPane.add(commentPane);
		add(wordCommentPane, BorderLayout.CENTER);
		revalidate();
	}

	/**
	 * display skip and end button
	 */
	private void displaySkipEnd() {
		JPanel skipEndPane = new JPanel(new GridLayout(1, 2));
		JButton skip = new JButton("Skip");
		skip.addActionListener(this);
		JButton end = new JButton("End");
		end.addActionListener(this);
		skipEndPane.add(skip);
		skipEndPane.add(end);
		add(skipEndPane,BorderLayout.SOUTH);
	}

	private void endGame() {
		removeAll();
		String finalScore = "Your Final Score is " + logic.getScore() + " / " + logic.getTimesAttempted();
		JLabel finalScoreLabel = new JLabel(finalScore,  SwingConstants.CENTER);
		add(finalScoreLabel, BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * this method performs the main game play of the Language Game
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		String text = ((AbstractButton) e.getSource()).getText();
		// display different versions of game
		if (text.equals("Word Game")) {
			version = "Word Game";
			logic = new GameLogic(version);
			logic.updateWord();
			removeAll();
			createView();
		} else if (text.equals("Recycle Game")) {
			version = "Recycle Game";
			logic = new GameLogic(version);
			logic.updateWord();
			removeAll();
			createView();
		}
		else if (text.equals("Skip")) {
			logic.updateWord();
			createView();
		}
		else if (text.equals("End")) {
			endGame();
		}
		// compare selected category with the original
		else {
			correct = logic.compare(text);
			logic.updateWord();
			gameStart = true;
			// refresh display
			createView();
		}
	}
}
