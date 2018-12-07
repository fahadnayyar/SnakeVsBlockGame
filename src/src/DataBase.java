package GuiEngine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * this class is serialized and deserialized. It stores the saved state of players and games.
 * @author fahadnayyar
 *
 */
public class DataBase implements Serializable{ // not used till now
	private ArrayList<Player> PlayersAccounts=new ArrayList<>();
	private ArrayList<Scores> LeaderBoard=new ArrayList<>();
	/**
	 * returns arraylist of leaderboard.
	 * @return
	 */
	
	public ArrayList<Scores> getLeaderBoard(){
		return LeaderBoard;
	}
	/**
	 * code for adding new player in the database.
	 * @param userName
	 * @param Password
	 * @return
	 */
	public Player addNewAccount(String userName, String Password) {
		Player player = authenticate(userName, Password);
		if (player==null) {
			player = new Player(userName, Password);
		//	Snake snake = new Snake(250, 500, 15, player);
			PlayersAccounts.add(player);
			//System.out.println(PlayersAccounts.size());
			return player;
		}
		else {
			return null;
		}
		
		
	}
	/**
	 * code for logging in into existing account.
	 * @param userName
	 * @param password
	 * @return
	 */
	public Player login(String userName, String password) {
		Player toret = authenticate(userName, password);
		if (toret != null) {
			return toret;
		}else {
			return null;
		}
	}
	/**
	 * code for authemtification used while suign-up and sign-in.
	 * @param userName
	 * @param password
	 * @return
	 */
	public Player authenticate(String userName, String password) {
		//System.out.println(PlayersAccounts.size()+" "+userName+" "+password);
		for (Player p : this.PlayersAccounts) {
		//	System.out.println(p.getName()+" "+p.getPassword());
			if ( p.getName().equals(userName) && p.getPassword().equals(password)) {
				return p;
			}
		}
		return null;
	}
	/**
	 * code whoch updates the leaderboard.
	 * @param x
	 * @param val
	 */
	public void updateLeaderBoard(String x,int val) {
		Scores score=new Scores(x,val);
		if(LeaderBoard.size()<=10) {
			LeaderBoard.add(score);
			return;
		}
		for(int i=0;i>9;i++) {
			if(LeaderBoard.get(i).compareTo(score)<0) {
				LeaderBoard.add(score);
				LeaderBoard.remove(LeaderBoard.get(LeaderBoard.size()-1));
				return;
			}
		}
		
	}
}	
/**
 * class of scores used to store leaderboard information.
 * @author fahadnayyar
 *
 */
class Scores implements Comparable<Scores>,Serializable{
	private int score;
	private String player;
	/**
	 * constructor.
	 * @param s
	 * @param x
	 */
	public Scores(String s,int x) {
			score=x;
			player=s;
	}
	/**
	 * getter of name.
	 * @return
	 */
	public String getName() {
		return player;
	}
	/**
	 * fetting of score.
	 * @return
	 */
	public int getScore() {
		return score;
	}
	/**
	 * comparing method.
	 */
	@Override
	public int compareTo(Scores other) {
		if(this.score> other.score) {
			return 1;
		}
		else if(this.score==other.score) {
			return 0;
		}
		else {
			return -1;
		}
	}
}