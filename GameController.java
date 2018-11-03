import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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
	Font font = new Font("Comic Sans MS", Font.PLAIN, 24);
	Font buttonFont = new Font("Comic Sans MS", Font.PLAIN, 20);
	Font wordFont  = new Font("Comic Sans MS", Font.BOLD, 30);
	Font commentFont = new Font("Comic Sans MS", Font.PLAIN, 24);
	Font instructionFont = new Font("Comic Sans MS", Font.PLAIN, 14);
	Color background = new Color(175, 206, 250);
	Color red = new Color(255, 200, 200);
	Color green = new Color(172, 236, 169);
	Color yellow = new Color(235, 235, 194);

	/**
	 * constructor
	 */
	public GameController() {
		super(new BorderLayout());
		setFocusable(true);
		gameStart = false;
		setBackground(background);
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		displayVersion();
	}

	/**
	 * display two buttons for word and recycling game
	 */
	private void displayVersion() {
		JPanel versionPane = new JPanel(new GridLayout(1, 2));
		// create buttons
		JButton wordVersion = new JButton("Word Game");
		wordVersion.setFont(font);
		wordVersion.setBackground(red);
		wordVersion.addActionListener(this);
		JButton recycleVersion = new JButton("Recycle Game");
		recycleVersion.setFont(font);
		recycleVersion.setBackground(green);
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
		//Font font = new Font("Comic Sans MS", Font.BOLD, 30);
		
		JPanel categoryScorePane = new JPanel(new GridLayout(2, 1));
		categoryScorePane.setBackground(background);
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
		catgry1.setBackground(green);
		catgry2.setBackground(red);
		catgry3.setBackground(yellow);
		catgry1.setFont(buttonFont);
		catgry2.setFont(buttonFont);
		catgry3.setFont(buttonFont);
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
		wordCommentPane.setBackground(background);
		JLabel wordLabel = new JLabel(logic.getWord().getData(), SwingConstants.CENTER);
		wordLabel.setFont(wordFont);
		wordCommentPane.add(wordLabel);

		// display comment or instructions
		JPanel commentPane = new JPanel(new BorderLayout());
		commentPane.setBackground(background);
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
			commentLabel.setFont(commentFont);
			commentPane.add(commentLabel, BorderLayout.CENTER);
		} else {
			// at the beginning on the game, before any clicks
			// display instructions
			String instructions = "<html>How to Play: <br/> A word will appear on the screen.<br/>You must decide which "
					+ "category it belongs to.<br/>When you have decided, click the corresponding button at the top.<br/>"
					+ "If you get it right, you will get a point!<br/>Once you have clicked, a new word will appear."
					+ "<br/>Try to get through all the words with the highest score possible!</html>";
			JLabel instructionLabel = new JLabel(instructions, SwingConstants.CENTER);
			instructionLabel.setFont(instructionFont);
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
		//skip button
		JButton skip = new JButton("Skip");
		skip.setFont(font);
		skip.setBackground(new Color(235, 235, 194));
		skip.addActionListener(this);
		//end button
		JButton end = new JButton("End");
		end.setFont(font);
		end.setBackground(green);
		end.addActionListener(this);
		
		
		skipEndPane.add(skip);
		skipEndPane.add(end);
		add(skipEndPane,BorderLayout.SOUTH);
	}

/**
	 * end game and display final score
	 * 
	 * @return true if score is more than half attempted names
	 */
	private boolean endGame() {
		removeAll();
		String finalScore = "Your Final Score is " + logic.getScore() + " / " + logic.getTimesAttempted();
		JLabel finalScoreLabel = new JLabel(finalScore, SwingConstants.CENTER);
		finalScoreLabel.setFont(commentFont);
		add(finalScoreLabel, BorderLayout.CENTER);
		revalidate();
		return (logic.getScore() >= logic.getTimesAttempted() / 2);
	}

	/**
	 * play sound effect for buttons
	 */
	private void sound(String kind) {
		try {
			File soundFile = null;
			if (kind.equals("button")) {
				// Open an audio input stream.
				soundFile = new File("button.wav");
			} else if (kind.equals("cheer")) {
				// Open an audio input stream.
				soundFile = new File("cheer.wav");
			}
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method performs the main game play of the Language Game
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		String text = ((AbstractButton) e.getSource()).getText();
		sound("button");
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
		} else if (text.equals("Skip")) {
			logic.updateWord();
			createView();
		} else if (text.equals("End")) {
			if (endGame())
				sound("cheer");
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
