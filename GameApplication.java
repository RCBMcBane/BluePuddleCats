import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 * Main method for LearningGame
 * 
 * @author BluePuddleCats
 * @version 11.2.18
 */
public class GameApplication {
	/**
	 * Create the JFrame in which the game will be played. 
	 * Initiate game play by calling GameController.
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

		try {
			// Open an audio input stream.
			File soundFile = new File("music.wav"); 
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY); 
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
