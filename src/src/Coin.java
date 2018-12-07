package GuiEngine;


import com.sun.javafx.geom.Shape;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
/**
 * coin class is a token which is an imageview type. Snake collects it to increase coin count of player.
 * @author fahadnayyar
 *
 */
public class Coin extends Tokens{
	/**
	 * constructor of coin
	 * @param Xlimit
	 * @param Ylimit
	 * @param x
	 * @param y
	 * @param myParent
	 * @param height
	 * @param width
	 * @param image
	 */
	public Coin(double Xlimit, double Ylimit, double x, double y, StackPane myParent,double height, double width, Image image) {
		 super(Xlimit, Ylimit, x, y, myParent, height, width, image);
		 
		 
		}
	/**
	 *  methods for collecting tokens by snake.
	 */
	@Override
	public void collectToken(Snake s) {
		super.RemoveFromParent();
		s.getPlayer().setNoOfCoins((s.getPlayer().getNoOfCoins() +1));
	}
}
