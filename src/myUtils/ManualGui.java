package myUtils;

import java.util.List;

import gameClient.MyGameGUI;

/**
 * This class represents the manual mode
 * @author ido shapira & edut cohen
 */
public class ManualGui implements Runnable {

	private static int prev_key;
	private static int prev_idRobot;

	private int key;
	private int idRobot;

	/**
	 * @param key
	 * @param idRobot
	 */
	public ManualGui(int key, int idRobot) {
		this.key = key;
		this.idRobot = idRobot;
	}

	public synchronized void setKey(int key) {
		this.key = key;
	}

	public synchronized void setIdRobot(int idRobot) {
		this.idRobot = idRobot;
	}

	@Override
	public void run() {
		while(MyGameGUI.game.isRunning()) {
			List<String> log = MyGameGUI.game.move();
			if(key != -1 && idRobot != -1) {
				if(prev_key != key || prev_idRobot != idRobot) {
					MyGameGUI.game.chooseNextEdge(idRobot,key);
					System.out.println("robot "+ idRobot+ " moved to: " +key +"\n" +log);
				}
				prev_key = key;
				prev_idRobot = idRobot;
			}
		}

	}


}
