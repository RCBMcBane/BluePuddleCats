/**
 * @author BluePuddleCat
 * @version 11/2/18
 * Written for HackHolyoke 2018
 *
 */
public class Word {
	String data, category;
	
  /**
	 * constructor
	 */
	public Word() {
		this.data = "";
		this.category = "";
	}
	
  /**
	 * @param data String
	 * setData
	 */
	public void setData(String data) {
		this.data = data;
	}
	
  /**
	 * getData
	 * @return String, data in Word
	 */
	public String getData() {
		return this.data;
	}
	
  /**
	 * @param category String, category of word
	 * setCategory
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
  /**
	 * getCategory
	 */
	public String getCategory() {
		return this.category;
	}

}
