package GuiEngine;

import java.io.FileInputStream;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
/**
 * panes class is used to make snake.
 * @author fahadnayyar
 *
 */
public class Panel extends Group{ // think about setters and getters.
	public static final double Xlimit = 500; // should not it be taken as parameter in constructor?
	public static final double Ylimit = 1000;// should not it be taken as parameter in constructor?
	private static int flag = 0; // better randomization!
	private final SnakeVsBlockGame myGame; 
	private final Image ballImage ; // are the images required as attributes?
	private final Image CoinImage;
	private final Image DestroyBlockImage;
	private final Image MagnetImage;
	private final Image ShieldImage ;
	public Panel(SnakeVsBlockGame myGame,Image ballImage, Image CoinImage,Image DestroyBlockImage, Image MagnetImage, Image ShieldImage  ) {
		this.myGame = myGame;
		this.ballImage = ballImage;
		this.CoinImage = CoinImage;
		this.DestroyBlockImage = DestroyBlockImage;
		this.MagnetImage = MagnetImage;
		this.ShieldImage = ShieldImage;
	}

	public Group createPanel() { // major method which generates random panels(group) to be added to the Hboxes.
		// think a lot about randomization and how to link it to the settings!
		if (flag == 1) {
			flag = 0;
			return pane2();
		}
		Random a = new Random();
		int x = a.nextInt(100);
		if (x <= 40) {
			flag = 1;
			return pane1();
		} 
		else if (x <= 70) {
			return pane2();
		}
		else {
			StackPane sp = null;
			return this;
		}

	}

	public Group pane1() {

		StackPane blockStackPane = new StackPane();
		blockStackPane.setTranslateX(Xlimit / 2);
		blockStackPane.setTranslateY(-Ylimit / 20);
		blockStackPane.setPrefHeight(Ylimit / 10);
		blockStackPane.setPrefWidth(Xlimit);
		// think a lot about randomization and how to link it to the settings!
		for (int i = 0; i < 5; i++) {
			Random ra = new Random();
			boolean x = ra.nextBoolean();
			if (x) {
				Hittable NewBlock = new Block(Xlimit, Ylimit, (i) * (Xlimit / 5), -Ylimit / 10, blockStackPane);
			//	myGame.addHittableObject(NewBlock);
			}
		}
	
		super.getChildren().add(blockStackPane);
	
		myGame.addGroup(this);
		return this;
	}

	public Group pane2() {
		StackPane WallTokenStackPane = new StackPane();
		WallTokenStackPane.setTranslateX(Xlimit / 2);
		WallTokenStackPane.setTranslateY(-Ylimit / 20);
		WallTokenStackPane.setPrefHeight(Ylimit / 10);
		WallTokenStackPane.setPrefWidth(Xlimit);
		int flag2=0;
		int flag3=0;
		// think a lot about randomization and how to link it to the settings!
		for (int i = 0; i < 5; i++) {
			Random ra = new Random();
			int x = ra.nextInt(100) + 1;
			if(flag2==1 || flag3>=1) {
				int y=ra.nextInt(4);
				if(flag3<2 && flag2==0) {
					flag3+=1;
					Tokens NewToken = new Ball(Xlimit, Ylimit, 2 * i * 50, -100, WallTokenStackPane, 30, 30, this.ballImage);
				}
				else if(y==0 && flag3<1) {
					flag3+=1;
					Tokens NewToken = new Ball(Xlimit, Ylimit, 2 * i * 50, -100, WallTokenStackPane, 30, 30, this.ballImage);
				}
				else if(y==1) {
					Hittable NewWall = new Wall(Xlimit, Ylimit, 2 * (i) * 50, -Ylimit / 10, WallTokenStackPane);
				}
			}
			
			else if (1 <= x && x <= 25) {
				Hittable NewWall = new Wall(Xlimit, Ylimit, 2 * (i) * 50, -Ylimit / 10, WallTokenStackPane);
				//myGame.addHittableObject(NewWall);
			} else if (26 <= x && x <= 50) {
				int y = ra.nextInt(100) + 1;
				if (y <= 68) {
					flag3+=1;
					Tokens NewToken = new Ball(Xlimit, Ylimit, 2 * i * 50, -100, WallTokenStackPane, 30, 30, this.ballImage);
					//myGame.addToken(NewToken);
				}
				else if (y <= 76) {
					flag2=1;
					Tokens NewToken = new Magnet(Xlimit, Ylimit, 2 * i * 50, -100, WallTokenStackPane, 50, 50, this.MagnetImage);
					//myGame.addToken(NewToken);
				} else if (y <= 84) {
					flag2=1;
					Tokens NewToken = new Coin(Xlimit, Ylimit, 2 * i * 50, -100, WallTokenStackPane, 30, 30, this.CoinImage);
					//myGame.addToken(NewToken);
				} else if (y <= 92) {
					flag2=1;
					Tokens NewToken = new Shield(Xlimit, Ylimit, 2 * i * 50, -100, WallTokenStackPane, 50, 50, this.ShieldImage);
					//myGame.addToken(NewToken);
				} else {
					flag2=1;
					Tokens NewToken = new DestroyBlocks(Xlimit, Ylimit, 2 * i * 50, -100, WallTokenStackPane, 50, 50, this.DestroyBlockImage);
					//myGame.addToken(NewToken);
				}
			}
		}
		super.getChildren().add(WallTokenStackPane);
		myGame.addGroup(this);
		return this;
	}


}
