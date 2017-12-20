package fr.utbm.tr54.threads;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;

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
		if(message.getRobotsInfo() != null) {				
			sendMessage();
		}
	}
	
	
	public void run() {
		try {
			Thread.sleep(timeWaiting*1000);
		} catch (InterruptedException e) {}
			
	}
	
	public void sendMessage() throws SocketException {
		broadcast.broadcast(message.getByteBufferMessage());
	
	}

}
