package GuiEngine;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
/**
 * this class represent page for leaderboard.
 * @author fahadnayyar
 *
 */
public class LeaderBoardPageScene extends Scenes{
/**
 * constructor.
 * @param game
 */
	public LeaderBoardPageScene(SnakeVsBlockGame game) {
	super(game);
}

	/**
	 * this method returns the scene of leaderboard. It also has handlers for buttons on the scene. The returned scene id used to set primarystage of game.
	 */
@Override
public Scene giveScene() {
	Label LeaderBoardPage =new Label("LEADERBOARD");
	LeaderBoardPage.setId("Leaderboard");
	Label Rank=new Label("RANK");
	Label Name=new Label("NAME");
	Label Score=new Label("SCORE");
	Label Date=new Label("DATE");
	Rank.setId("Rank");
	Name.setId("Name");
	Score.setId("Score");
	Date.setId("Date");
	Label Rank1=new Label("1");
	Rank1.setId("rank1");
	Label Rank2=new Label("2");
	Rank2.setId("rank2");
	Label Rank3=new Label("3");
	Rank3.setId("rank3");
	Label Rank4=new Label("4");
	Rank4.setId("rank4");
	Label Rank5=new Label("5");
	Rank5.setId("rank5");
	Label Rank6=new Label("6");
	Rank6.setId("rank6");
	Label Rank7=new Label("7");
	Rank7.setId("rank7");
	Label Rank8=new Label("8");
	Rank8.setId("rank8");
	Label Rank9=new Label("9");
	Rank9.setId("rank9");
	Label Rank10=new Label("10");
	Rank10.setId("rank10");
	
	ArrayList<Scores> LeaderBoard=myGame.getMyDataBase().getLeaderBoard();
	System.out.println(LeaderBoard);
	int l=LeaderBoard.size();
	Label[] Names=new Label[l];
	Label[] ScoresOfPlayers=new Label[l];	
	for(int i=0;i<l;i++) {
		Names[i]=new Label(LeaderBoard.get(i).getName());
		ScoresOfPlayers[i]=new Label(Integer.toString(LeaderBoard.get(i).getScore()));
		Names[i].setTextFill(Color.BLUE);
		Names[i].setTranslateX(50);
		Names[i].setTranslateY(-150+50*i);
//		Names[i].setId("names".concat(Integer.toString(i)));
//		ScoresOfPlayers[i].setId("scores".concat(Integer.toString(i)));
		ScoresOfPlayers[i].setTextFill(Color.BLUE);
		ScoresOfPlayers[i].setTranslateX(-50);
		ScoresOfPlayers[i].setTranslateY(-150+50*i);
	}
	Button b=new Button("GoToMainPage");
	b.setId("Button");
	b.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			myGame.setPrimaryStage(myGame.GetMainPageScene());
		}
	});
	
	StackPane sp=new StackPane();
	sp.setId("pane");
	sp.getChildren().addAll(LeaderBoardPage,Rank,Name,Score,Date,Rank1,Rank2,Rank3,Rank4,Rank5,Rank6,Rank7,Rank8,Rank9,Rank10,b);
	sp.getChildren().addAll(Names);
	sp.getChildren().addAll(ScoresOfPlayers);
	sp.getStylesheets().add(getClass().getResource("LeaderBoardPage.css").toExternalForm());
	myScene = new Scene(sp,500,1000);
	myScene.setFill(Color.BLACK);
	
	return myScene;
}

}
