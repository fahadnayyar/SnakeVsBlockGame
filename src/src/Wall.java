package GuiEngine;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * wall is a rectangle that is a hittable object. It restricts the lateral motion of snake.
 * @author fahadnayyar
 *
 */
public class Wall extends Rectangle implements Hittable{
	private StackPane GuiParent; 
	private final double height; 
	private final double width; 
	private double currentX; 
	private double currentY; 
	private final double Xlimit; 
	private final double Ylimit; 
	
	/**
	 * constructor of wall class.
	 * @param Xlimit
	 * @param Ylimit
	 * @param x
	 * @param y
	 * @param myParent
	 */
	public Wall(double Xlimit,double Ylimit, double x, double y, StackPane myParent) {
		this.GuiParent = myParent;
		this.height = Ylimit/10; 
		this.width = Xlimit/100;
		this.currentX = x; 
		this.currentY = y;
		this.Xlimit = Xlimit;
		this.Ylimit = Ylimit;
		
		
		super.setWidth(this.width);
		super.setHeight(this.height);
		super.setTranslateX(x);
		super.setTranslateY(y);
		super.setFill(Color.WHITE);
		myParent.getChildren().add(this);
	}
	
	/**
	 * hitting logic of walls
	 */
	@Override
	public void hit(Snake s) {
		RemoveFromScene();
	}
	/**
	 * gives y coordinate of walls.
	 */
	@Override
	public double GetTranslateY() { 
		return super.getParent().getParent().getTranslateY(); 
	}

	/**
	 * removes the wall from scene.
	 */
	@Override
	public void RemoveFromScene() {
		this.GuiParent.getChildren().remove(this);
	}
	/**
	 * gives the x coordinate of wall.
	 */
	@Override
	public double GetTranslateX() {
		return this.currentX; 
	}
	/**
	 * no use of this method.
	 */
	public int getValue() {
		return -1;
	}
}
