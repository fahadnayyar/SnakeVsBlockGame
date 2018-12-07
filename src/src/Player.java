package GuiEngine;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * players are serializrd, It stores the state of current game instance.
 * @author fahadnayyar
 *
 */
public class Player implements Serializable {
	// constructor
	/**
	 * constructor.
	 * @param name
	 * @param password
	 */
	public Player(String name, String password) {
		this.name = name;
		this.password = password;
		LengthOfMySnake=5;
		Level=1;
	}
	
	private static long serialVersionUID=120L;
	private boolean sound;
	private boolean music;
	private int LastScore=0;
	private int Level;
	
	private int LengthOfMySnake;
	// at-1
	private boolean lastGameENded = true;
	// at-2
	private boolean isMagnetCollected = false;
	// at -3
	private int magnetcounter = 0;
	// at -4
	private boolean isShielded = false;
	// at -5
	private int Shieldcounter = 0;
	// at -6
	private int maxScore = 0;
	// at -7
	private int currentScore;
	// at -8
	private int noOfCoins;
	// at -9
	//private Snake mysnake;
	// at -10 (f)
	private final String name;
	// at -11 (f)
	private final String password;

	
	
	/**
	 * below are all the setters and getters of attributes of player.
	 * @return
	 */
	
	public boolean isSound() {
		return sound;
	}
	public void setSound(boolean sound) {
		this.sound = sound;
	}
	public boolean isMusic() {
		return music;
	}
	public void setMusic(boolean music) {
		this.music = music;
	}

	
	
	
	// at -1 (g)
	
	public int getLevel() {
		return Level;
	}
	public void setLevel(int v) {
		Level=v;
	}
	
	public int getLengthOfMySnake() {
		return LengthOfMySnake;
	}
	public void setLengthOfMySnake(int length) {
		LengthOfMySnake=length;
	}
	
	public boolean isLastGameENded() {
		return lastGameENded;
	}

	// at -1 (s)
	public void setLastGameENded(boolean lastGameENded) {
		this.lastGameENded = lastGameENded;
	}

	// at -2 (g)
	public boolean isMagnetCollected() {
		return isMagnetCollected;
	}

	// at -2 (s)
	public void setMagnetCollected(boolean isMagnetCollected) {
		this.isMagnetCollected = isMagnetCollected;
	}

	// at -3 (g)
	public int getMagnetcounter() {
		return magnetcounter;
	}

	// at -3 (s)
	public void setMagnetcounter(int magnetcounter) {
		this.magnetcounter = magnetcounter;
	}

	// at -4 (g)
	public boolean isShielded() {
		return isShielded;
	}

	// at -4 (s)
	public void setShielded(boolean isShielded) {
		this.isShielded = isShielded;
	}

	// at -5 (g)
	public int getShieldcounter() {
		return Shieldcounter;
	}

	// at -5 (s)
	public void setShieldcounter(int shieldcounter) {
		Shieldcounter = shieldcounter;
	}

	// at -6 (g)
	public int getMaxScore() {
		return maxScore;
	}

	// at -6 (s)
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	// at -7 (g)
	public int getCurrentScore() {
		return this.currentScore;
	}

	// at -7 (s)
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	// at -8 (g)
	public int getNoOfCoins() {
		return noOfCoins;
	}

	// at -8 (s)
	public void setNoOfCoins(int noOfCoins) {
		this.noOfCoins = noOfCoins;
	}

	// at -9 (g)
//	public Snake getMysnake() {
//		return mysnake;
//	}

	// at -9 (s)
//	public void setSnake(Snake s) {
//		mysnake = s;
//	}

	// at -10 (g) (f)
	public String getName() {
		return name;
	}

	// at -11 (g) (f)
	public String getPassword() {
		return password;
	}

	public int getLastScore() {
		return LastScore;
	}
	
	/**
	 * refreshes the content of player.
	 */
	public void refresh() {
			LengthOfMySnake=5;
			isMagnetCollected=false;	
			isShielded=false;
			LastScore=currentScore;
			currentScore=0;
			magnetcounter=0;
			Shieldcounter=0;
	
	}
}
