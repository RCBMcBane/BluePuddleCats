import javax.swing.JFrame;
/**
 * @author BluePuddleCats
 * @version 11.2.18
 * main method for LearningGame
 */
public class GameApplication {
	/**
	 * create the JFrame in which the game will be played
   * initiate game play by calling gameController
	 */
	public static void main( String[] args ) {
		// create a new JFrame to display the game
		JFrame gameFrame = new JFrame("Game Frame");
		
		// set size
		gameFrame.setSize( 2000, 2000 );

		//call gameController to play game
    gameFrame.add( new GameController());

		// exit normally on closing the window
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// show frame
		gameFrame.setVisible(true);
	}
}
