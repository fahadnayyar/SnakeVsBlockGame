package GuiEngine;

import com.sun.javafx.geom.Shape;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * magnet class is a token which is an imageview type. Snake collect it to further collect multiple coins.
 * @author fahadnayyar
 *
 */
public class Magnet extends Tokens{
	public Magnet(double Xlimit, double Ylimit, double x, double y, StackPane myParent,double height, double width, Image image) {
		 super(Xlimit, Ylimit, x, y, myParent, height, width, image);
		 
		 
		}
	/**
	 *  methods for collecting tokens by snake.
	 */
	@Override
	public void collectToken(Snake s) {
		super.RemoveFromParent();
		s.getPlayer().setMagnetCollected(true);
	}
}
