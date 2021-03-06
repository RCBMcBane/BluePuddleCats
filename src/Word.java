/**
 * 
 * This class creates a word object for the Learning Game
 * which has two instance variables: data and category.
 *
 * @author BluePuddleCat
 * @version 11/3/18
 * Written for HackHolyoke 2018
 *
 */
public class Word {
	String data; //the actual word
	String category; //the category the word is in
	
	/**
 	 * constructor
 	 */
	public Word() {
		//default constructor
	}
	
  	/**
	 * @param data String, word itself
	 * setter method for Data
	 */
	public void setData(String data) {
		this.data = data;
	}
	
  	/**
	 * getter method for Data
	 * @return String, data in Word
	 */
	public String getData() {
		return this.data;
	}
	
 	 /**
	 * @param category String, category of word
	 * setter method for Category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
  	/**
	 * getter method for category
	 * @return String category of word
	 */
	public String getCategory() {
		return this.category;
	}

}
