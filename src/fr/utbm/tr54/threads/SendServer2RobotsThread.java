package fr.utbm.tr54.threads;

import java.io.IOException;

import fr.utbm.tr54.message.ServerRobotMes;
import lejos.network.BroadcastManager;


public class SendServer2RobotsThread  extends Thread{
	private ServerRobotMes message;
	private int timeWaiting;
	private BroadcastManager broadcast;
	
	public SendServer2RobotsThread(ServerRobotMes msg, int timeWaiting) throws IOException {



		this.message = msg;
		this.timeWaiting = timeWaiting;
		broadcast=BroadcastManager.getInstance(8888);
	}
	
	
	public void run() {
		//broadcast.broadcast(message.getByteMessage());

			
		try {
			Thread.sleep(timeWaiting);
		} catch (InterruptedException e) {}
			
	}

}
