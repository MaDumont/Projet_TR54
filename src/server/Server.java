package server;

import java.util.LinkedList;

import Threads.SendServer2RobotsThread;
import fr.utbm.tr54.message.RobotServerMes;
import fr.utbm.tr54.message.SeverRobotMes;


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
		
		SendServer2RobotsThread  communicationThread =new SendServer2RobotsThread(new SeverRobotMes(null, null),5);
		communicationThread.start();


		while(true) {
			if(! communicationThread.isAlive()) {
				communicationThread =new SendServer2RobotsThread(new SeverRobotMes(null, null),5);
				communicationThread.start();

			}
		}
		
		
	}
	
}
