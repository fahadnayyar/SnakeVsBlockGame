package GuiEngine;

import com.sun.javafx.image.impl.ByteIndexed.Getter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * this class represents the scene of sign in page.
 * @author fahadnayyar
 *
 */
public class SignInPageScene extends Scenes{
	private Label NoPlayer;
	/**
	 * constructor.
	 * @param game
	 */
	public SignInPageScene(SnakeVsBlockGame game) {
		super(game);
	}

	/**
	 * return the scene. It has buttons and logic for adding new users and authentificating users. This returned scene is used to set the promarystage of game.
	 */
	@Override
	public Scene giveScene() {
		Label SignIn=new Label("SIGN IN");
		Label UserName=new Label("Username");
		Label Password=new Label("Password");
		TextField Username=new TextField();
		TextField PassWord=new TextField();
		Button SubmitButton=new Button("SUBMIT");
		Button BackButton=new Button("BACK");
		BackButton.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetLoginPageScene());
			}
		});	
		SignIn.setId("SignInLabel");
		UserName.setId("UserLabel");
		Password.setId("PasswordLabel");
		Username.setId("UserText");
		PassWord.setId("PasswordText");
		SubmitButton.setId("SubmitButton");
		BackButton.setId("BackButton");
		VBox SignInPageVb=new VBox(50,SignIn,UserName,Username,Password,PassWord,BackButton,SubmitButton);
		SignInPageVb.setTranslateY(100);
		SignInPageVb.setAlignment(Pos.TOP_CENTER);
		StackPane MainPageStackpane=new StackPane(SignInPageVb);
		MainPageStackpane.getStylesheets().add(getClass().getResource("SignInPage.css").toString());
		myScene= new Scene(MainPageStackpane,500,1000);
		myScene.setFill(Color.BLACK);
		SubmitButton.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Player newPLayer = myGame.getMyDataBase().login(Username.getText(), PassWord.getText());
				//System.out.println(newPLayer);
				if (newPLayer != null) {
					myGame.setCurrentPlayer(newPLayer);
					myGame.setPrimaryStage(myGame.GetMainPageScene());
				}else {
					if(NoPlayer==null) {
						NoPlayer=new Label("Invalid User/Password");
						SignInPageVb.getChildren().add(NoPlayer);
					}//System.out.println("katta");
				//myGame.setPrimaryStage(myGame.GetGamePlayScene());
				}
			}
		});	

		return myScene;
	}
}
