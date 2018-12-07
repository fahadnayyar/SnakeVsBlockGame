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
 * first page which is visible to a user.
 * @author fahadnayyar
 *
 */
public class LoginPageScene extends Scenes{
	/**
	 * constructor if login page,
	 * @param game
	 */
	public LoginPageScene(SnakeVsBlockGame game) {
		super(game);
	}

	/**
	 * method to give scene of login page to set it into promary stage of game.
	 */
	@Override
	public Scene giveScene() {
		Button SignUpButton=new Button("SIGN UP");
		SignUpButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetSignUpPageScene());
			}
		});
		Button SignInButton=new Button("SIGN IN");
		SignInButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetSignInPageScene());
			}
		});
		VBox LoginPageVb=new VBox(50,SignInButton,SignUpButton);
		LoginPageVb.setTranslateY(100);
		LoginPageVb.setAlignment(Pos.TOP_CENTER);
		StackPane LoginPageStackpane=new StackPane(LoginPageVb);
		LoginPageStackpane.getStylesheets().add(getClass().getResource("LoginPage.css").toString());
		myScene= new Scene(LoginPageStackpane,500,1000);
		myScene.setFill(Color.BLACK);
		return myScene;
	}
}
