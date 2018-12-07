package GuiEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
	/**
	 * this class represents the page/scene of gameOver.
	 * @author fahadnayyar
	 *
	 */
public class GameOverScene extends Scenes{ //not integrated till now
	/**
	 * constructor.
	 * @param game
	 */
	public GameOverScene(SnakeVsBlockGame game) {
		super(game);
	}

	/**
	 * this method returns the scene of gameover and have handler of buttons.
	 */
	@Override
	public Scene giveScene() {
		StackPane p=new StackPane();
		p.setId("mypane");
		p.getStylesheets().add(getClass().getResource("GameOver.css").toExternalForm());
		Label score =new Label("Score:".concat(Integer.toString(myGame.getCurrentPlayer().getLastScore())));
		score.setTranslateX(0);
		score.setTranslateY(-350);
		score.setTextFill(Color.BLUE);
		score.setId("score");
		Label Name=new Label(myGame.getCurrentPlayer().getName());
		Name.setId("name");
		
		Button b=new Button("GoToMainPage");
		b.setId("Button");
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetMainPageScene());
			}
		});
		
		p.getChildren().addAll(Name,score,b);
		myScene= new Scene(p,500,1000);
		myScene.setFill(Color.BLACK);
		return myScene;
	}
	
}
