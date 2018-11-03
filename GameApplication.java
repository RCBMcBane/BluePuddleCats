import javax.swing.JFrame;

/**
 * Main method for LearningGame
 * 
 * @author BluePuddleCats
 * @version 11.2.18
 */
public class GameApplication {
	/**
	 * create the JFrame in which the game will be played initiate game play by
	 * calling GameController
	 */
	public static void main(String[] args) {
		// create a new JFrame to display the game
		JFrame gameFrame = new JFrame("BluePuddleCat Education Game");

		// set size
		gameFrame.setSize(600, 600);

		// call gameController to play game
		gameFrame.add(new GameController());

		// exit normally on closing the window
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// show frame
		gameFrame.setVisible(true);
	}
}
