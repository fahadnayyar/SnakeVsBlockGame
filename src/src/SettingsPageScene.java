package GuiEngine;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
/**
 * settings page scene class.
 * @author fahadnayyar
 *
 */
public class SettingsPageScene extends Scenes{
	/**
	 * 
	 * @param game
	 */
	public SettingsPageScene(SnakeVsBlockGame game) {
		super(game);
	}
	
	/**
	 *  class which return the settingsPage scene to set into the primary stage of game.
	 *  it also has inner handlers to the buttons of this page.
	 */
	@Override
	public Scene giveScene() {
		Label Settings=new Label("SETTINGS");
		Settings.setId("Settings");
		Label Speed=new Label("SPEED:");
		Label Music=new Label("MUSIC");
		Label Sound=new Label("SOUND");
		Speed.setId("Speed");
		Music.setId("Music");
		Sound.setId("Sound");
		RadioButton SpeedSlow=new RadioButton("Slow");
		SpeedSlow.setId("Slow");
		SpeedSlow.setTranslateX(0);
		SpeedSlow.setTranslateY(-100);
		RadioButton SpeedModerate=new RadioButton("Moderate");
		SpeedModerate.setId("Moderate");
		RadioButton SpeedFast=new RadioButton("Fast");
		SpeedFast.setId("Fast");
		ToggleGroup SpeedGroup = new ToggleGroup();  
		SpeedSlow.setToggleGroup(SpeedGroup);
		SpeedModerate.setToggleGroup(SpeedGroup);
		SpeedFast.setToggleGroup(SpeedGroup);
		RadioButton MusicOn=new RadioButton("On");
		RadioButton MusicOff=new RadioButton("Off");
		RadioButton SoundOn=new RadioButton("On");
		RadioButton SoundOff=new RadioButton("Off");
		ToggleGroup MusicGroup = new ToggleGroup();  
		MusicOff.setToggleGroup(MusicGroup);
		MusicOn.setToggleGroup(MusicGroup);
		ToggleGroup SoundGroup = new ToggleGroup();  
		SoundOn.setToggleGroup(SoundGroup);
		SoundOff.setToggleGroup(SoundGroup);
		SpeedFast.setToggleGroup(SoundGroup);
		MusicOn.setId("MusicOn");
		MusicOff.setId("MusicOff");
		SoundOn.setId("SoundOn");
		SoundOff.setId("SoundOff");
		
		SpeedGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
				RadioButton Speed=(RadioButton)t1.getToggleGroup().getSelectedToggle();
				if(Speed==SpeedSlow) {
					myGame.getCurrentPlayer().setLevel(1);
				}
				else if(Speed==SpeedModerate) {
					myGame.getCurrentPlayer().setLevel(2);
				}
				else if(Speed==SpeedFast) {
					myGame.getCurrentPlayer().setLevel(3);
				}
			}
		});
		MusicGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
				RadioButton Music=(RadioButton)t1.getToggleGroup().getSelectedToggle();
				if(Music==MusicOff) {
					myGame.getCurrentPlayer().setMusic(false);
				}
				else if(Music==MusicOn) {
					myGame.getCurrentPlayer().setMusic(true);
				}
			}
		});
		SoundGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
				RadioButton Sound=(RadioButton)t1.getToggleGroup().getSelectedToggle();
				if(Sound==SoundOff) {
					myGame.getCurrentPlayer().setSound(false);
				}
				else if(Sound==SoundOn) {
					myGame.getCurrentPlayer().setSound(true);
				}
			}
		});
		Button b=new Button("GoToMainPage");
		b.setId("Button");
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				myGame.setPrimaryStage(myGame.GetMainPageScene());
			}
		});
		StackPane sp=new StackPane();
		sp.getChildren().addAll(Settings,Speed,Music,Sound,SpeedSlow,SpeedFast,SpeedModerate,MusicOff,MusicOn,SoundOff,SoundOn,b);
		sp.setId("pane");
		myScene = new Scene(sp,500,1000);
		myScene.setFill(Color.BLACK);
		sp.getStylesheets().add(getClass().getResource("SettingsPage.css").toExternalForm());
		return myScene;
	}
	
	
}
