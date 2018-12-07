package GuiEngine;

import com.sun.javafx.geom.Shape;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
/**
 * shield class is a token which is an imageview type. It protects the player from decresing length against blocks.
 * @author fahadnayyar
 *
 */
public class Shield extends Tokens{
	public Shield(double Xlimit, double Ylimit, double x, double y, StackPane myParent,double height, double width, Image image) {
		 super(Xlimit, Ylimit, x, y, myParent, height, width, image);
		 
		 
		}
	/**
	 *  methods for collecting tokens by snake.
	 */
	@Override
	public void collectToken(Snake s) {
		super.RemoveFromParent();
		s.getPlayer().setShielded(true);
	}
}
