package GuiEngine;

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
 * 
 * @author fahadnayyar
 *
 */
public class SignUpPageScene extends Scenes{
	/**
	 * constructor.
	 * @param game
	 */
	public SignUpPageScene(SnakeVsBlockGame game) {
		super(game);
	}

	/**
	 * return the scene of signUp page. It has the logic for buttons and authentificationf of new users.
	 */
	@Override
	public Scene giveScene() {
		
		Label SignUp=new Label("SIGN UP");
		Label UserName=new Label("Username");
		Label Password=new Label("Password");
		Label ConfirmPassword=new Label("Confirm Password");
		TextField Username=new TextField();
		TextField PassWord=new TextField();
		TextField ConfirmPassWord=new TextField();
		
		Button SubmitButton=new Button("SUBMIT");

		Button BackButton=new Button("BACK");
		BackButton.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetLoginPageScene());
			}
		});	
		
		SignUp.setId("SignUpLabel");
		UserName.setId("UserLabel");
		Password.setId("PasswordLabel");
		ConfirmPassword.setId("ConfirmPasswordLabel");
		Username.setId("UserText");
		PassWord.setId("PasswordText");
		ConfirmPassWord.setId("ConfirmPasswordText");
		SubmitButton.setId("SubmitButton");
		BackButton.setId("BackButton");
		VBox SignUpPageVb=new VBox(40,SignUp,UserName,Username,Password,PassWord,ConfirmPassword,ConfirmPassWord,BackButton,SubmitButton);
		SignUpPageVb.setTranslateY(100);
		SignUpPageVb.setAlignment(Pos.TOP_CENTER);
		StackPane MainPageStackpane=new StackPane(SignUpPageVb);
		MainPageStackpane.getStylesheets().add(getClass().getResource("SignUpPage.css").toString());
		myScene= new Scene(MainPageStackpane,500,1000);
		myScene.setFill(Color.BLACK);
		
		
		
		SubmitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (PassWord.getText().equals(ConfirmPassWord.getText())) {
					Player newplayer =myGame.getMyDataBase().addNewAccount(Username.getText(), PassWord.getText());
					if(newplayer==null) {
						Label PlayerALreadyExists=new Label("Error! UserAlreadyExists");
						SignUpPageVb.getChildren().add(PlayerALreadyExists);
					}
					else {
						myGame.setCurrentPlayer(newplayer);
						myGame.setPrimaryStage(myGame.GetMainPageScene());
					}
				}
				else {
					Label NotSamePassword=new Label("Error! Passwords not same");
					SignUpPageVb.getChildren().add(NotSamePassword);
				}
				
				
				
				
				
				//myGame.setPrimaryStage(myGame.GetGamePlayScene());
			}
			
		});
		return myScene;
	}
}
