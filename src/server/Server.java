package server;

import java.util.LinkedList;

import Threads.SendServer2RobotsThread;
import communication.MsgRobot2Server;
import communication.MsgServer2Robots;

public class Server {
	

	
	public static void main(String[] args){
		
		//put only the id of the robot
		LinkedList<Robot> listRobots = new LinkedList<>();
		
		
		//if connection
		if(true) {
			listRobots.add(new Robot());
		}
		
		//if robot out of zone
		if(true) {
			listRobots.removeFirst();
		}
		
		SendServer2RobotsThread  communicationThread =new SendServer2RobotsThread(new MsgServer2Robots(),5);
		communicationThread.start();


		while(true) {
			if(! communicationThread.isAlive()) {
				communicationThread =new SendServer2RobotsThread(new MsgServer2Robots(),5);
				communicationThread.start();

			}
		}
		
		
	}
	
}
