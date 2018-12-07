package GuiEngine;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


/**
 * ball class is a token which is an imageview type. Snake collect it to increase length.
 * @author fahadnayyar
 *
 */
public class Ball extends Tokens{
	/**
	 * value store value on block.
	 */
	private final int value;
	/**
	 * text is gui of value
	 */
	private final Text mytext;
	//private final StackPane myparent;
	
	
	/**
	 * constructor of ball
	 * @param Xlimit
	 * @param Ylimit
	 * @param x
	 * @param y
	 * @param myParent
	 * @param height
	 * @param width
	 * @param image
	 */
	public Ball(double Xlimit, double Ylimit, double x, double y, StackPane myParent,double height, double width, Image image) {
		super(Xlimit, Ylimit, x, y, myParent, height, width, image);
		
		Random ra=new Random(); // How many random objects to generate and how to coordinate them according to the game setting?
		this.value=ra.nextInt(2)+1; // think about upper limit of this random. try to relate it to the game logic/ setting!
		mytext= new Text(Integer.toString(this.value));
		mytext.setTranslateX(x); // use?
		mytext.setTranslateY(y); // use?
		this.setFitHeight(30);
		this.setFitWidth(25);
		this.GuiParent.getChildren().addAll(mytext); // adding the text to stackpage (parent) which ultimately gets added to the group and therefor made to move together. 
	}
	/**
	 *  methods for collecting tokens by snake.
	 */
	@Override
	public void collectToken(Snake s) {
		this.RemoveFromParent();
		for(int i=0;i<this.value;i++) {
			s.AddChild();
		}	
	}
	/**
	 * method for removal of ball from scenegraph.
	 */
	@Override
	public void RemoveFromParent() {
		this.GuiParent.getChildren().remove(mytext);
		super.RemoveFromParent();
	}


}
