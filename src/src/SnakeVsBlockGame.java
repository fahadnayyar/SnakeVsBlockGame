
package GuiEngine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
	/**
	 * this is the main game block/controller.
	 * @author fahadnayyar
	 *
	 */
public class SnakeVsBlockGame extends Application {
	/**
	 * entry point of application.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * start of application.
	 */
	@Override
	public void start(Stage primaryStage) {
		this.currentGroups = new ArrayList<Group>();
		this.children = new ArrayList<Node>();
		this.childrenNext = new ArrayList<Node>();
		Collections.synchronizedList(this.childrenNext);
		Collections.synchronizedList(this.children);
		Collections.synchronizedList(this.currentGroups);
		try {
			this.PrimaryStage = primaryStage;
			DataBase db = null;
			try {
				db = deSerializeDataBase();
				System.out.println(db);
			} catch (Exception e) {
				System.out.println("error diring desialization at start");
				System.out.println(e.getMessage());
			}
			if (db == null) {
				this.myDataBase = new DataBase();
			} else {
				this.myDataBase = db;
			}

			primaryStage.setScene(GetLoginPageScene());
			primaryStage.setTitle("SnakeVsBlockGame");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		startGameLogic();
	}
	/**
	 * serialization of database.
	 * @throws IOException
	 */
	public void serializeDataBase() throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("DataBase.txt"));
			out.writeObject(this.myDataBase);
		} finally {
			out.close();
		}
	}
	
	/**
	 * deserialization of batabase.
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public DataBase deSerializeDataBase() throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		DataBase toret = null;
		try {
			in = new ObjectInputStream(new FileInputStream("DataBase.txt"));
			toret = (DataBase) in.readObject();
			return toret;
		} finally {
			in.close();
			
		}
	}
	/**
	 * methos used to set the primary stage into various scenes.
	 * @param scene
	 */
	// at 6 - (s) // at -6 does not require any getter.
	public void setPrimaryStage(Scene scene) {

		this.PrimaryStage.hide();
		; // is this garbage collected?
		this.PrimaryStage = new Stage();
		PrimaryStage.setScene(scene);
		PrimaryStage.setTitle("SnakeVsBlockGame");
		PrimaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {

				try {
					serializeDataBase();
				}

				catch (Exception e) {
					System.out.println("error during serialization at exiting window");
					System.out.println(e.getMessage());
				}
			}
		});
		PrimaryStage.show();
	}

	
	
	private Snake currentSnake;
	
	public Snake getCurrentSnake() {
		return currentSnake;
	}

	public void setCurrentSnake(Snake currentSnake) {
		this.currentSnake = currentSnake;
	}

/**
 * attributes of game:
 */
	private Timeline mainTime;
	private KeyFrame MainKey;
	private Timeline t1;
	private Timeline t2;
	// at 1
	private DataBase myDataBase;
	// at 2
	/**
	 * araylist of groups. This is the major array for traversals.
	 */
	public ArrayList<Group> currentGroups;
	// at 3
	private Player currentPlayer;
	// at 4
	public final double Xlimit = 500;
	// at 5
	public final double Ylimit = 1000;
	// at 6
	private Stage PrimaryStage;
	// at 7
	private Group currentGroup;
	// at 8
	private Group nextGroup;
	// at 9
	private ArrayList<Node> childrenNext;
	// at 10
	private ArrayList<Node> children;


	/**
	 * SETTERS AND GETTERS OF ATTRIBUTE OF PLAYER.
	 * @param t1
	 */
	
	public void setTimelinet1(Timeline t1) {
		this.t1=t1;
	}
	
	public void setTimelinet2(Timeline t2) {
		this.t2=t2;
	}
	public Timeline getTimelinet1() {
		return t1;
	}
	public Timeline getTimelinet2() {
		return t2;
	}
	public Timeline getMainTimeline() {
		return mainTime;
	}
	
	public Stage getPrimaryStage() {
		return PrimaryStage;
	}
	
	// at 1 - (g)
	public DataBase getMyDataBase() {
		return myDataBase;
	}

	// at 1 - (s)
	public void setMyDataBase(DataBase myDataBase) {
		this.myDataBase = myDataBase;
	}

	// at 2 - (g)
	public ArrayList<Group> getCurrentGroups() {
		return currentGroups;
	}

	// at 2 - (s)
	public void setCurrentGroups(ArrayList<Group> currentGroups) {
		this.currentGroups = currentGroups;
	}

	// at 3 - (g)
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	// at 3 - (s)
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	// at 4 - (g) (f)
	public double getXlimit() {
		return this.Xlimit;
	}

	// at 5 - (g) (f)
	public double getYlimit() {
		return this.Ylimit;
	}

	// at 7 (g)
	public Group getCurrentGroup() {
		return currentGroup;
	}

	// at 7 (s)
	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}

	// at 8 (g)
	public Group getNextGroup() {
		return nextGroup;
	}

	// at 8 (s)
	public void setNextGroup(Group nextGroup) {
		this.nextGroup = nextGroup;
	}

	// at 9 (g)
	public ArrayList<Node> getChildrenNext() {
		return childrenNext;
	}

	// at 9 (s)
	public void setChildrenNext(ArrayList<Node> childrenNext) {
		this.childrenNext = childrenNext;
	}

	// at 10 (g)
	public ArrayList<Node> getChildren() {
		return children;
	}

	// at 10 (s)
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	public void addGroup(Group group) {
		currentGroups.add(group);
	}
	/**
	 * STARTING THE GAME LOGIC.
	 */
	public void startGameLogic() {
		mainTime = new Timeline();
//		Timeline currenttime = new Timeline();
//		currenttime.setCycleCount(Animation.INDEFINITE);
		mainTime.setCycleCount(Animation.INDEFINITE);
		MainKey = new KeyFrame(Duration.seconds(0.02), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if (currentSnake != null) {
					currentSnake.setLeftSeized(false);
					currentSnake.setRightSeized(false);
				}

				if (currentGroups.size() >= 1) {
					nextGroup = currentGroups.get(0);
				}
				if (nextGroup != null) {
					StackPane spNext = (StackPane) nextGroup.getChildren().get(0);
					ObservableList<Node> childrenNextobs = spNext.getChildren();
					if (nextGroup != null && nextGroup.getTranslateY() >= 135 && nextGroup.getTranslateY() <= 137) {
						for (Node child : childrenNextobs) {
							if (child instanceof Hittable) {
								collideHittable((Hittable) child, childrenNext);
							} else if (child instanceof Tokens) {
								collideToken((Tokens) child, children);
							}
						}
						removeObjects(childrenNext);
						childrenNext = new ArrayList<>();
					} else if (nextGroup.getTranslateY() >= 138) {
						currentGroup = nextGroup;
						currentGroups.remove(nextGroup);
					}
				}
				if (currentGroup != null) {
					StackPane sp = (StackPane) currentGroup.getChildren().get(0);
					ObservableList<Node> childrenobs = sp.getChildren();
					if (currentGroup != null && currentGroup.getTranslateY() >= 138) {
						if (currentPlayer.isMagnetCollected()) {
							currentPlayer.setMagnetcounter(currentPlayer.getMagnetcounter() + 1);
						}
						if (currentPlayer.isShielded()) {
							currentPlayer.setShieldcounter(currentPlayer.getShieldcounter() + 1);
						}
						for (Node child : childrenobs) {
							if (child instanceof Hittable) {
								TouchHittable(child);
							} else if (child instanceof Tokens) {
								if (currentPlayer.isMagnetCollected() && child.getClass() == Ball.class) {
									children.add(child);
								}
								collideToken((Tokens) child, children);
							}
						}
						if (currentPlayer.getMagnetcounter() == 12) {
							currentPlayer.setMagnetCollected(false);
							currentPlayer.setMagnetcounter(0);

						}
						if (currentPlayer.getShieldcounter() == 12) {
							currentPlayer.setShielded(false);
							currentPlayer.setShieldcounter(0);
						}
						removeObjects(children);
						children = new ArrayList<>();
					}
				}
			}
		});
		mainTime.getKeyFrames().add(MainKey);
		mainTime.play();
	}
	/**
	 * COLLING METHOD FOR TOKENS.
	 * @param token
	 * @param todeleteT
	 * @return
	 */
	public boolean collideToken(Tokens token, ArrayList<Node> todeleteT) {
		double ty = token.getTranslateY();
		double tx = token.getTranslateX();
		double sy = currentSnake.getTranslateY();
		double sx = currentSnake.getTranslateX();
		if (ty <= 210 && ty >= 137) {
			if (-10 <= sx && sx <= 10 && tx == 0) {
				todeleteT.add(token);
			} else if (90 <= sx && sx <= 110 && tx == 100) {
				todeleteT.add(token);
			} else if (190 <= sx && sx <= 210 && tx == 200) {
				todeleteT.add(token);
			} else if (290 <= sx && sx <= 310 && tx == 300) {
				todeleteT.add(token);
			} else if (390 <= sx && sx <= 410 && tx == 400) {
				todeleteT.add(token);
			}
		}
		return true;
	}
	
	/**
	 * colliding logic for hittables.
	 * @param tohit
	 * @param todeleteH
	 * @return
	 */
	public boolean collideHittable(Hittable tohit, ArrayList<Node> todeleteH) {
		double sx = currentSnake.getTranslateX();
		double hx = tohit.GetTranslateX();
		double sy = currentSnake.getTranslateY();
		double hy = tohit.GetTranslateY();
		if (tohit.getClass() == Block.class && 135 <= hy && hy <= 137) {

			if (-35 <= sx && sx < 50 && hx == 0) {
				todeleteH.add((Block) tohit);
			} else if (50 <= sx && sx < 150 && hx == 100) {
				todeleteH.add((Block) tohit);
			} else if (150 <= sx && sx < 250 && hx == 200) {
				todeleteH.add((Block) tohit);
			} else if (250 <= sx && sx < 350 && hx == 300) {
				todeleteH.add((Block) tohit);
			} else if (350 <= sx && sx <= 450 && hx == 400) {
				todeleteH.add((Block) tohit);
			}
		}
		return true;
	}
	/**
	 * removing objects from group array.
	 * @param node
	 */
	public void removeObjects(ArrayList<Node> node) {
		for (Node child : node) {
			if (child instanceof Hittable) {
				((Hittable) child).hit(currentSnake);
			} else if (child instanceof Tokens) {
				((Tokens) child).collectToken(currentSnake);
			}
		}
	}

	/**
	 * touching logic for hittables.
	 * @param node
	 */
	public void TouchHittable(Node node) {
		if (node instanceof Hittable) {
			Hittable tohit = (Hittable) node;
			double sx = currentSnake.getTranslateX();
			double hx = tohit.GetTranslateX();
			double sy = currentSnake.getTranslateY();
			double hy = tohit.GetTranslateY();
			if (node.getClass() == Block.class && 138 <= hy && hy <= 250) {

				if (sx <= 65 && hx == 0) {
					currentSnake.setLeftSeized(true);
				} else if (sx >= 35 && hx == 100) {
					currentSnake.setRightSeized(true);
				} else if (sx <= 165 && hx == 100) {
					currentSnake.setLeftSeized(true);
				} else if (sx >= 135 && hx == 200) {
					currentSnake.setRightSeized(true);
				} else if (sx <= 265 && hx == 200) {
					currentSnake.setLeftSeized(true);
				} else if (sx >= 235 && hx == 300) {
					currentSnake.setRightSeized(true);
				} else if (sx <= 365 && hx == 300) {
					currentSnake.setLeftSeized(true);
				} else if (sx >= 335 && hx == 400) {
					currentSnake.setRightSeized(true);
				}

			}
			System.out.println(sx);
			if (node.getClass() == Wall.class && 138 <= hy && hy <= 250) {

				if (sx >= -20 && sx <= 0 && hx == 0) {
					currentSnake.setRightSeized(true);
				} else if (sx >= 70 && sx <= 100 && hx == 100) {
					currentSnake.setRightSeized(true);
				} else if (sx >= 170 && sx >= 200 && hx == 200) {
					currentSnake.setRightSeized(true);
				} else if (sx >= 270 && sx <= 300 && hx == 300) {
					currentSnake.setRightSeized(true);
				} else if (sx >= 370 && sx <= 400 && hx == 400) {
					currentSnake.setRightSeized(true);
				} else if (sx >= 0 && sx <= 20 && hx == 0) {
					currentSnake.setLeftSeized(true);
				} else if (sx >= 100 && sx <= 120 && hx == 100) {
					currentSnake.setLeftSeized(true);
				} else if (sx >= 200 && sx <= 220 && hx == 200) {
					currentSnake.setLeftSeized(true);
				} else if (sx >= 300 && sx <= 320 && hx == 300) {
					currentSnake.setLeftSeized(true);
				} else if (sx >= 400 && sx <= 420 && hx == 400) {
					currentSnake.setLeftSeized(true);
				}
			}
		}
	}

	/**
	 * methods to set the primarystage to different scenes.
	 * @return
	 */
	public Scene GetMainPageScene() {
		MainPageScene mainPageScene = new MainPageScene(this);
		return mainPageScene.giveScene();
	}

	public Scene GetGamePlayScene() {
		GamePlayScene gamePlayScene = new GamePlayScene(this);
		return gamePlayScene.giveScene();
	}

	public Scene GetSettingsPageScene() {
		SettingsPageScene settingsPageScene = new SettingsPageScene(this);
		return settingsPageScene.giveScene();
	}

	public Scene GetLeaderBoardPageScene() {
		LeaderBoardPageScene leaderBoardPageScene = new LeaderBoardPageScene(this);
		return leaderBoardPageScene.giveScene();
	}

	public Scene GetGameOverScene() {
		GameOverScene gameOverScene = new GameOverScene(this);
		return gameOverScene.giveScene();
	}

	public Scene GetLoginPageScene() {
		LoginPageScene LoginPageScene = new LoginPageScene(this);
		return LoginPageScene.giveScene();
	}

	public Scene GetSignInPageScene() {
		SignInPageScene SignInPageScene = new SignInPageScene(this);
		return SignInPageScene.giveScene();
	}

	public Scene GetSignUpPageScene() {
		SignUpPageScene SignUpPageScene = new SignUpPageScene(this);
		return SignUpPageScene.giveScene();
	}

}