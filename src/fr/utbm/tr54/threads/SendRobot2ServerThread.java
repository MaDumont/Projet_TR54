package fr.utbm.tr54.threads;

import java.io.IOException;
import java.net.SocketException;

import fr.utbm.tr54.message.RobotServerMes;
import lejos.network.BroadcastManager;

public class SendRobot2ServerThread  extends Thread{
	private RobotServerMes message;
	private int timeWaiting;
	private BroadcastManager broadcast;
	
	public SendRobot2ServerThread(RobotServerMes msg, int timeWaiting, BroadcastManager broadcast) throws IOException {
		
		this.message = msg;
		this.timeWaiting = timeWaiting;
		this.broadcast=broadcast;
	}
	
	
	public void run() {		

		System.out.println("thread : " + message.getRobotId());		
		try {
			broadcast.broadcast(message.getByteBufferMessage());
			Thread.sleep(timeWaiting);
		} catch (InterruptedException | SocketException e) {}
			
	}

}
