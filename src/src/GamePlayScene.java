package GuiEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

//import application.SnakeVsBlockGame.KeyPressedHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
/**
 * major class which returns the scene/page of main gameplay.
 * @author fahadnayyar
 *
 */
public class GamePlayScene extends Scenes { 
	private final Image ballImage = new Image(getClass().getResourceAsStream("ball.png"));
	private final Image CoinImage = new Image(getClass().getResourceAsStream("coin.png"));
	private final Image DestroyBlockImage = new Image(getClass().getResourceAsStream("DestroyBlock.png"));
	private final Image MagnetImage = new Image(getClass().getResourceAsStream("Magnet.png"));
	private final Image ShieldImage = new Image(getClass().getResourceAsStream("Shield.png"));
	private Menu Token;
	private Menu score;
	private Menu LengthOfSnake;
	private Menu Coins;
	private Timeline t1;
	private Timeline t2;
	private KeyFrame k1;
	private KeyFrame k2;
	/**
	 * constructor of gameplay scene
	 * @param game
	 */
	public GamePlayScene(SnakeVsBlockGame game) {
		super(game);
	}
	/**
	 * this mehtod returns the scene of maingameplay which is set in the primarystage of game.
	 */
	@Override
	public Scene giveScene() {
		
		Media media = new Media(new File("../GuiEngine/music1.mp3").toURI().toString());  
        
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
 
        mediaPlayer.setAutoPlay(true);
		myGame.setCurrentGroups(new ArrayList<>());
		BorderPane GameplayStackPane = new BorderPane(); 
		GameplayStackPane.setId("BorderPane"); 
		GameplayStackPane.setTranslateX(-200);
		GameplayStackPane.setTranslateY(0); 
		myScene = new Scene(GameplayStackPane, Xlimit, Ylimit); 
		myScene.setFill(Color.BLACK);
		
		if (myGame.getCurrentPlayer().isLastGameENded()) {
			Snake snake = new Snake(250, 500, 15, myGame.getCurrentPlayer(),myGame);
			myGame.setCurrentSnake(snake);
			GameplayStackPane.getChildren().add(snake);
		}else {
			Snake snake = new Snake(250, 500, 15, myGame.getCurrentPlayer(),myGame);
			
			GameplayStackPane.getChildren().add(myGame.getCurrentSnake());
		}
		
		
		Button b = new Button("GoToMainPage");
		b.setId("Button");
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetMainPageScene());
			}
		});
		Menu menu = new Menu("");
		menu.setId("menuPlay");
		MenuItem Gotomainpage = new MenuItem("Exit");
		MenuItem Restart = new MenuItem("Restart");

		menu.getItems().add(Gotomainpage);
		menu.getItems().add(Restart);

		score = new Menu("Score: 00");
		score.setId("text");
		
		LengthOfSnake = new Menu("Length: 00");
		LengthOfSnake.setId("length");
		
		Coins = new Menu("Coin: 00");
		Coins.setId("coinprop");
		
		Token  = new Menu();
		Token.setId("token");
		
		Gotomainpage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetMainPageScene());
			}
		});

		Restart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetGamePlayScene());

			}
		});
		
		menu.setOnShowing(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				t1.pause();
				t2.pause();
				myGame.getMainTimeline().pause();
			}
		});
		
		menu.setOnHiding(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				t1.play();
				t2.play();
				myGame.getMainTimeline().play();
			}
		});
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menu,Token,score,Coins,LengthOfSnake);
		VBox vb = new VBox(menuBar);
		vb.setPrefHeight(10);
		vb.prefWidth(50);
		vb.setTranslateX(200);
		vb.setTranslateY(0);
		GameplayStackPane.setCenter(vb);

		GameplayStackPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		myGame.getCurrentPlayer().setLastGameENded(false);
		setSnakeMotion(myGame.getCurrentSnake());
		myScene.setOnKeyPressed(myGame.getCurrentSnake().getHnadler());
		myScene.setOnKeyReleased(myGame.getCurrentSnake().getSTopHandler());
		setBackground(myScene);
		return myScene;
	}
	/**
	 * this methos sets the keyboard motion of snake.
	 * @param s
	 */
	public void setSnakeMotion(Snake s) {
		
		Timeline t =new Timeline();
		t.setCycleCount(Animation.INDEFINITE);
		KeyFrame k = new KeyFrame(Duration.millis(0.5), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (!s.isLeftSeized() &&s.getCanMoveLeft()) {
					s.setTranslateX(s.getTranslateX()-0.1);
				}else if (!s.isRightSeized() && s.getCanMoveRight()) {
					s.setTranslateX(s.getTranslateX()+0.1);
				}
			}

		});
		t.getKeyFrames().add(k);
		t.play();
	}
	/**
	 * this methos sets the ogic of generation and movement of background (including tokens, block and walls).
	 * @param ss
	 */
	public void setBackground(Scene ss) {
		final BorderPane root = (BorderPane) ss.getRoot();
		t1 = new Timeline();
		t1.setCycleCount(Animation.INDEFINITE);
		t2 = new Timeline();
		t2.setCycleCount(Animation.INDEFINITE);
		ArrayList<HBox> HBoxArray = new ArrayList<>();
		Collections.synchronizedList(HBoxArray);

		k1 = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Random ra = new Random();
				HBox h1 = new HBox();
				h1.setPrefHeight(Ylimit / 10);
				h1.setPrefWidth(Xlimit);
				h1.setTranslateX(0);
				h1.setTranslateY((Ylimit / 25));
				Panel p = new Panel(myGame,ballImage, CoinImage,DestroyBlockImage, MagnetImage, ShieldImage  );
				h1.getChildren().add(p.createPanel());
				root.getChildren().add(h1);
				HBoxArray.add(h1);
			}

		});
		k2 = new KeyFrame(Duration.seconds(0.02), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if ( myGame.getCurrentPlayer().isMagnetCollected()) {
					Token.setGraphic(new ImageView(MagnetImage));
					
				}else if (myGame.getCurrentPlayer().isShielded()){
					Token.setGraphic(new ImageView(ShieldImage));
				}
				else {
					Token.setGraphic(null);
				}
				score.setText("Score: ".concat(Integer.toString(myGame.getCurrentPlayer().getCurrentScore())));
				LengthOfSnake.setText("Length: ".concat(Integer.toString(myGame.getCurrentSnake().getNoOfBalls())));
				Coins.setText("Coins: ".concat(Integer.toString(myGame.getCurrentPlayer().getNoOfCoins())));
				
				
				for (HBox h : HBoxArray) {
					h.setTranslateY(h.getTranslateY() + 1);
					Group r = (Group) h.getChildren().get(0);
					r.setTranslateY(r.getTranslateY() + 1);

					if (!r.getChildren().isEmpty()) {
						StackPane stackPane = (StackPane) r.getChildren().get(0);
						ObservableList<Node> things = stackPane.getChildren();
						for (Node yo : things) {
							yo.setTranslateY(r.getTranslateY());
						}
					}

				}
				ArrayList<HBox> toremove = new ArrayList<>();
				for (HBox h : HBoxArray) {

					if (h.getTranslateY() > 600) {
						h.getChildren().remove(0);
						toremove.add(h);
					}
				}
				for (HBox h : toremove) {
					HBoxArray.remove(h);
				}
				toremove = null;
			}
		});

		t1.getKeyFrames().add(k1);
		t1.play();
		t2.getKeyFrames().add(k2);
		t2.play();
		myGame.setTimelinet1(t1);
		myGame.setTimelinet2(t2);
	}

	/**
	 * this method controls the speed of the game on basis of the length of snake and difficulty level.
	 */
	public void controlKeyframes() {
		Timeline Control=new Timeline();
		Control.setCycleCount(Animation.INDEFINITE);
		KeyFrame ControlKey=new KeyFrame(Duration.seconds(0.02), new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				double d=(double)myGame.getCurrentPlayer().getLevel();
				double l=(double)myGame.getCurrentPlayer().getLengthOfMySnake();
				
				if(myGame.getCurrentPlayer().isShielded()) {
					t1.setRate(5*d);
					t2.setRate(5*d);
					myGame.getMainTimeline().setRate(5*d);
				}
				else if(0<l && l<=5) {
					t1.setRate(d);
					t2.setRate(d);
					myGame.getMainTimeline().setRate(d);
				}
				else if(5<l && l<=10) {
					t1.setRate(2*d);
					t2.setRate(2*d);
					myGame.getMainTimeline().setRate(2*d);
				}
				else if(10<l && l<=15) {
					t1.setRate(3*d);
					t2.setRate(3*d);
					myGame.getMainTimeline().setRate(3*d);
				}
				else if(15<l && l<=20) {
					t1.setRate(4*d);
					t2.setRate(4*d);
					myGame.getMainTimeline().setRate(4*d);
				}
				else if(20<l) {
					t1.setRate(5*d);
					t2.setRate(5*d);
					myGame.getMainTimeline().setRate(5*d);
				}
				
			}
		});
	}

}
