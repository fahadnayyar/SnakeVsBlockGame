package GuiEngine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
 /**
  * token is an abstract imageview and it is super-class of ball, destroyblock, shield, coin and magnet.
  * @author fahadnayyar
  *
  */
public abstract class Tokens extends ImageView { 
	private double CurentX; 
	private double CurrentY; 
	private final double Xlimit; 
	private final double Ylimit;
	protected final StackPane GuiParent; 
									
	private final Image myImage; 
									
	private final double height; 
	private final double width; 

	
	
	/**
	 * constructor of abstract class token.
	 * @param Xlimit
	 * @param Ylimit
	 * @param x
	 * @param y
	 * @param myParent
	 * @param height
	 * @param width
	 * @param image
	 */
	public Tokens(double Xlimit, double Ylimit, double x, double y, StackPane myParent, double height, double width,
			Image image) {
		this.GuiParent = myParent;
		this.Xlimit = Xlimit;
		this.Ylimit = Ylimit;
		this.CurentX = x; // no use as for now
		this.CurrentY = y; // no use as for now
		this.width = width;
		this.height = height;
		this.myImage = image;

		super.setImage(myImage);
		super.setFitWidth(width); // read about this method.
		super.setFitHeight(height); // read about this method.
		super.setTranslateX(x); // use?
		super.setTranslateY(y); // use?

		myParent.getChildren().add(this);
	}
	/**
	 * abstract method implemented by actual tokens.
	 * @param s
	 */
	public abstract void collectToken(Snake s); // its abstract because on collecting the tokens different things will happen for different tokens.
	/**
	 * code that removes token from parent.
	 */
	public void RemoveFromParent() {
		this.GuiParent.getChildren().remove(this);
	}

}
