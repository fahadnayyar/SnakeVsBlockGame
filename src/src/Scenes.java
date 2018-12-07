package GuiEngine;

import javafx.scene.Scene;
/**
 * scenes is an abstract class. All scenes or pages of game extent this class.
 * @author fahadnayyar
 *
 */
public abstract class Scenes { 
	
	protected final double Xlimit = 500; 
	protected  final double Ylimit = 1000; 
	protected  SnakeVsBlockGame myGame; 
	protected  Scene myScene; 
	/**
	 * constructor for abstract super class scenes.
	 * @param game
	 */
	public Scenes(SnakeVsBlockGame game) {
		this.myGame = game;
	}
	/**
	 * abstract method which returns the scene to set in the primarystage of game.
	 * @return
	 */
	public abstract Scene giveScene();
}
