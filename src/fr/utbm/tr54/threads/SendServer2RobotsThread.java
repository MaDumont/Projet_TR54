package fr.utbm.tr54.threads;

import java.io.IOException;
import java.net.SocketException;

import fr.utbm.tr54.message.ServerRobotMes;
import lejos.network.BroadcastManager;


public class SendServer2RobotsThread  extends Thread{
	private ServerRobotMes message;
	private int timeWaiting;
	private BroadcastManager broadcast;
	
	public SendServer2RobotsThread(ServerRobotMes msg, int timeWaiting, BroadcastManager broadcast) throws IOException {



		this.message = msg;
		this.timeWaiting = timeWaiting;
		this.broadcast=broadcast;
	}
	
	
	public void run() {
		try {
			broadcast.broadcast(message.getByteBufferMessage());
			Thread.sleep(timeWaiting);
		} catch (InterruptedException | SocketException e) {}
			
	}

}
