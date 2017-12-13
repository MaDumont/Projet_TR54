package server;

import java.io.IOException;
import java.util.LinkedList;

import Threads.SendServer2RobotsThread;
import fr.utbm.tr54.message.Message;
import fr.utbm.tr54.message.RobotServerMes;
import fr.utbm.tr54.message.ServerRobotMes;
import fr.utbm.tr54.tp1.Clock;
import lejos.network.BroadcastManager;
import lejos.network.BroadcastReceiver;


public class Server {
	

	public static void main(String[] args) throws IOException{
		
		//put only the id of the robot
		LinkedList<Robot> listRobots = new LinkedList<>();
		Clock clock = new Clock();
		BroadcastManager broadcast;
		BroadcastReceiver receiver;

		//if connection
		if(true) {
			listRobots.add(new Robot());
		}
		
		//if robot out of zone
		if(true) {
			listRobots.removeFirst();
		}
		
		SendServer2RobotsThread  communicationThread = new SendServer2RobotsThread(new ServerRobotMes(clock.getTime(), null),5);
		communicationThread.start();

		broadcast = BroadcastManager.getInstance(9999);
		receiver = BroadcastReceiver.getInstance(8888);
		
		Message mes = new ServerRobotMes(clock.getTime(), null);

		broadcast.broadcast(mes.getByteBufferMessage());
		
		while(true) {
			if(! communicationThread.isAlive()) {
				communicationThread = new SendServer2RobotsThread(new ServerRobotMes(clock.getTime(), null),5);
				communicationThread.start();
			}
		}
		
		
	}
	
}
