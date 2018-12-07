package GuiEngine;

import java.util.Random;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * block class whos is a rectagle and is a hittable object. It decreases the
 * length of snake and increases the point of player.
 * 
 * @author fahadnayyar
 *
 */
public class Block extends Rectangle implements Hittable {
	private int value;
	private final StackPane GuiParent;
	private final double height;
	private final double width;
	private double currentX;
	private double currentY;
	private final double Xlimit;
	private final double Ylimit;
	private final Text mytext;

	/**
	 * constructor of block class.
	 * 
	 * @param Xlimit
	 * @param Ylimit
	 * @param x
	 * @param y
	 * @param myParent
	 */
	public Block(double Xlimit, double Ylimit, double x, double y, StackPane myParent) {

		this.GuiParent = myParent;
		this.currentX = x;
		this.currentY = y;
		super.setWidth(y);
		this.Xlimit = Xlimit;
		this.Ylimit = Ylimit;
		this.height = Ylimit / 10;
		this.width = Xlimit / 5;

		Random ra = new Random();

		super.setWidth(this.width);
		super.setHeight(this.height);
		super.setTranslateX(x);
		super.setTranslateY(y);
		super.setFill(Color.rgb(ra.nextInt(255), ra.nextInt(255), ra.nextInt(255)));
		super.setStrokeWidth(2);
		this.value = ra.nextInt(5) + 1;

		this.mytext = new Text(Integer.toString(this.value));
		this.mytext.setTranslateX(x);
		this.mytext.setTranslateY(y);
		myParent.getChildren().addAll(this, this.mytext);
		super.setArcWidth(30.0);
		super.setArcHeight(20.0);
	}

	/**
	 * return value of block.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * hitting/ colliding logic of block.
	 */
	@Override
	public void hit(Snake s) {
		RemoveFromScene();

		if (!s.getPlayer().isShielded() && this.value < s.getNoOfBalls()) {
			for (int i = 0; i < this.value; i++) {
				s.RemoveChild();
			}
			s.getPlayer().setCurrentScore(s.getPlayer().getCurrentScore() + this.value);
		} else if (s.getPlayer().isShielded()) {

		} else {
			s.getPlayer().setLastGameENded(true);
			SnakeVsBlockGame game = s.getMyGame();
			game.getMyDataBase().updateLeaderBoard(s.getPlayer().getName(), s.getPlayer().getCurrentScore());
			if (s.getPlayer().getCurrentScore() > s.getPlayer().getMaxScore()) {
				s.getPlayer().setMaxScore(s.getPlayer().getCurrentScore());

			}
			s.getPlayer().refresh();
			s.getMyGame().getMainTimeline().stop();
			s.getMyGame().getTimelinet1().stop();
			s.getMyGame().getTimelinet2().stop();
			s.getMyGame().setPrimaryStage(s.getMyGame().GetGameOverScene());
		}
	}

	/**
	 * gives y coordinate of block.
	 */
	@Override
	public double GetTranslateY() {
		return super.getParent().getParent().getTranslateY();
	}

	/**
	 * removes the block from scene.
	 */
	@Override
	public void RemoveFromScene() {
		this.GuiParent.getChildren().remove(this.mytext);
		this.GuiParent.getChildren().remove(this);
	}

	/**
	 * gives the x coordinate of block.
	 */
	@Override
	public double GetTranslateX() {
		return this.currentX;
	}

}
