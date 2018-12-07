package GuiEngine;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
/**
 * bestroy class is a token which is an imageview type. It destroys blocks and walls in the proximuty of snake's head.
 * @author fahadnayyar
 *
 */
public class DestroyBlocks extends Tokens{
	/**
	 * constructor of destroy blocks.
	 * @param Xlimit
	 * @param Ylimit
	 * @param x
	 * @param y
	 * @param myParent
	 * @param height
	 * @param width
	 * @param image
	 */
	public DestroyBlocks(double Xlimit, double Ylimit, double x, double y, StackPane myParent,double height, double width, Image image) {
		 super(Xlimit, Ylimit, x, y, myParent, height, width, image);
		 
		 
		}
	/**
	 *  methods for collecting tokens by snake.
	 */
	@Override
	public void collectToken(Snake s) {
		super.RemoveFromParent();
		ArrayList<Group> list=s.getMyGame().getCurrentGroups();
		ArrayList<Node> todelete=new ArrayList<>();
		for(Group group: list) {
			StackPane sp=(StackPane)group.getChildren().get(0);
			ObservableList<Node> child=sp.getChildren();
			for(Node n:child) {
				if(n instanceof Hittable) {
					todelete.add(n);
				}
			}
		}
		for(Node n:todelete) {
			((Hittable)n).RemoveFromScene();
		}
	}

}
