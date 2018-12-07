package GuiEngine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.function.IntToDoubleFunction;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
/**
 * snake class is a group. Represents the gui of snake. Has the logic for moving the snake.
 * @author fahadnayyar
 *
 */
public class Snake extends Group {
	// constructor
	public Snake(double x, double y, double r, Player player,SnakeVsBlockGame game) {
		this.myplayer = player;
		Face = new Circle(x, y, r, Color.CADETBLUE);
		LeftEye = new Circle(x - r / 2, y - r / 4, r / 4, Color.BLACK);
		RightEye = new Circle(x + r / 2, y - r / 4, r / 4, Color.BLACK);
		CentreX = x;
		CentreY = y;
		Radius = r;
		this.setTranslateY(60);
		this.setTranslateX(215);
		this.getChildren().add(Face);
		this.getChildren().add(LeftEye);
		this.getChildren().add(RightEye);
		Tail = new ArrayList<>();
		myGame=game;
//		AddChild();
//		AddChild();
//		AddChild();
//		AddChild();
//		AddChild();
		if(player.isLastGameENded()) {
			updateSnake(5);
		}
		else {
			updateSnake(player.getLengthOfMySnake());
		}
	}

	private SnakeVsBlockGame myGame;
	// at- 1
	private boolean canMoveLeft = false;
	// at- 2
	private boolean canMoveRight = false;
	// at- 3
	private int NoOfBalls = 0;
	// at- 4
	private ArrayList<Circle> Tail;
	// at- 5
	private Circle Face;
	// at- 6
	private int noOfCircle = 0;
	// at- 7
	private boolean leftSeized = false;
	// at- 8
	private boolean rightSeized = false;
	// at- 9
	private final double CentreX;
	// at- 10
	private final double CentreY;
	// at- 11
	private final double Radius;
	// at- 12
	private final Circle LeftEye;
	// at- 13
	private final Circle RightEye;
	// at- 14
	private final Player myplayer;

	public SnakeVsBlockGame getMyGame() {
		return myGame;
	}
	
	// at- 1 (g)
	public boolean getCanMoveLeft() {
		return canMoveLeft;
	}

	// at- 1 (s)
	public void setCanMoveLeft(boolean canMoveLeft) {
		this.canMoveLeft = canMoveLeft;
	}

	// at- 2 (g)
	public boolean getCanMoveRight() {
		return canMoveRight;
	}

	// at- 2 (s)
	public void setCanMoveRight(boolean canMoveRight) {
		this.canMoveRight = canMoveRight;
	}

	// at- 3 (g)
	public int getNoOfBalls() {
		return NoOfBalls;
	}

	// at- 3 (s)
	public void setNoOfBalls(int noOfBalls) {
		NoOfBalls = noOfBalls;
	}

	// at- 4 (g)
	public ArrayList<Circle> getTail() {
		return Tail;
	}

	// at- 4 (s)
	public void setTail(ArrayList<Circle> tail) {
		Tail = tail;
	}

	// at- 5 (g)
	public Circle getFace() {
		return Face;
	}

	// at- 5 (s)
	public void setFace(Circle face) {
		Face = face;
	}

	// at- 6 (g)
	public int getNoOfCircle() {
		return noOfCircle;
	}

	// at- 6 (s)
	public void setNoOfCircle(int noOfCircle) {
		this.noOfCircle = noOfCircle;
	}

	// at- 7 (g)
	public boolean isLeftSeized() {
		return leftSeized;
	}

	// at- 7 (s)
	public void setLeftSeized(boolean lestSeized) {
		this.leftSeized = lestSeized;
	}

	// at- 8 (g)
	public boolean isRightSeized() {
		return rightSeized;
	}

	// at- 8 (s)
	public void setRightSeized(boolean rightSeized) {
		this.rightSeized = rightSeized;
	}

	// at- 9 (g) (f)
	public double getCentreX() {
		return CentreX;
	}

	// at- 10 (g) (f)
	public double getCentreY() {
		return CentreY;
	}

	// at- 11 (g) (f)
	public double getRadius() {
		return Radius;
	}

	// at- 12 (g) (f)
	public Circle getLeftEye() {
		return LeftEye;
	}

	// at- 13 (g) (f)
	public Circle getRightEye() {
		return RightEye;
	}

	// at- 14 (g)
	public Player getPlayer() {
		return myplayer;

	}

	public void AddChild() {
		
		noOfCircle += 1;
		NoOfBalls += 1;
		this.getPlayer().setLengthOfMySnake(noOfCircle);
		Circle c = new Circle(CentreX, CentreY + (noOfCircle + 1) * Radius, Radius / 2, Color.BLUE);
		Tail.add(c);
		this.getChildren().add(c);
	}

	public void RemoveChild() {

		if (Tail.size() >= 1) {
			noOfCircle -= 1;
			NoOfBalls -= 1;
			this.getPlayer().setLengthOfMySnake(noOfCircle);
			Circle c = Tail.get(Tail.size() - 1);
			Tail.remove(c);
			this.getChildren().remove(c);
		} else {

		}
	}

	public EventHandler<KeyEvent> getHnadler() {
		return new KeyPressedHandler(this);
	}

	private class KeyPressedHandler implements EventHandler<KeyEvent> {
		private Snake mysnake;

		public KeyPressedHandler(Snake org) {
			mysnake = org;
		}

		@Override
		public void handle(KeyEvent e) {

			switch (e.getCode()) {
			case LEFT:
				mysnake.setCanMoveLeft(true);
				break;
			case RIGHT:
				mysnake.setCanMoveRight(true);
				break;

			default:
				break;
			}

			;

		}
	}

	public EventHandler<KeyEvent> getSTopHandler() {
		return new keyStopHandler(this);
	}

	private class keyStopHandler implements EventHandler<KeyEvent> {
		private Snake mysnake;

		public keyStopHandler(Snake s) {
			this.mysnake = s;

		}

		@Override
		public void handle(KeyEvent event) {
			switch (event.getCode()) {
			case LEFT:
				mysnake.setCanMoveLeft(false);
				break;
			case RIGHT:
				mysnake.setCanMoveRight(false);
				break;
			default:
				break;
			}

		}

	}

	public void updateSnake(int length) {
		for(int i=0;i<length;i++) {
			AddChild();
		}
	}
}
