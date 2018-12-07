package GuiEngine;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameOverPageGui extends Application{// useless class and file! just to test how gameOverScene looks like
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane p=new StackPane();
		p.setId("mypane");
		p.getStylesheets().add(getClass().getResource("GameOver.css").toExternalForm());
		Label Name=new Label("abde");
		Name.setId("name");
		Label score =new Label("Score: 00");
		score.setTranslateX(0);
		score.setTranslateY(-350);
		score.setTextFill(Color.BLUE);
		score.setId("score");
		Button b=new Button("GoToMainPage");
		b.setId("Button");
		p.getChildren().addAll(Name,score,b);
		Scene scene = new Scene(p,500,1000);
		scene.setFill(Color.BLACK);
		primaryStage.setTitle("SnakeVsBlockGame");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}