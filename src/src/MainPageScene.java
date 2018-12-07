package GuiEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * it is the mainPage scene class. It has 4 buttons: start, leaderbpard, resume, setting.
 * resume is visible only when last game was not completed.
 * @author fahadnayyar
 *
 */
public class MainPageScene extends Scenes{
	/**
	 * constructor of mainpagescene
	 * @param game
	 */
	public MainPageScene(SnakeVsBlockGame game) {
		super(game);
	}
	/**
	 * returns the scene of mainpage to add it into primaryStage of game. Has handlers of buttons of mainpage.
	 */
	@Override
	public Scene giveScene() {
		Button StartButton=new Button("START");
		StartButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetGamePlayScene());
			}
			
		});
		
		
		Button LeaderBoardPageButton=new Button("LEADERBOARD");
		LeaderBoardPageButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetLeaderBoardPageScene());
			}
			
		});
		
		
		Button SettingsButton=new Button("SETTINGS");
		SettingsButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetSettingsPageScene());
			}
			
		});
		
		
		Button ResumeGameButton=new Button("RESUME");
		ResumeGameButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetGamePlayScene());
			}
			
		});
		
		
		StartButton.setId("StartButton");
		LeaderBoardPageButton.setId("LeaderBoardPageButton");
		SettingsButton.setId("SettingsButton");
		ResumeGameButton.setId("ResumeGameButton");
		Label Score=new Label("LastScore ".concat(Integer.toString(myGame.getCurrentPlayer().getLastScore())));
		Score.setId("score");
		Label Name=new Label("Welcome! ".concat(myGame.getCurrentPlayer().getName()));
		
		
		VBox MainPageVb=new VBox(50,StartButton,LeaderBoardPageButton,SettingsButton,Score,Name);
		if (! myGame.getCurrentPlayer().isLastGameENded()) {
			MainPageVb.getChildren().add(ResumeGameButton);
		}
		
		MainPageVb.setTranslateY(100);
		MainPageVb.setAlignment(Pos.TOP_CENTER);
		StackPane MainPageStackpane=new StackPane(MainPageVb);
		MainPageStackpane.getStylesheets().add(getClass().getResource("MainPage.css").toString());
		myScene= new Scene(MainPageStackpane,500,1000);
		myScene.setFill(Color.BLACK);
		return myScene;
	}
}
