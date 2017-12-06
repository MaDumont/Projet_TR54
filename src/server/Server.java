package server;

import java.io.IOException;
import java.sql.Time;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


import Threads.SendServer2RobotsThread;
import fr.utbm.tr54.message.Information;
import fr.utbm.tr54.message.*;
import fr.utbm.tr54.tp1.Clock;
import lejos.network.BroadcastManager;
import lejos.network.BroadcastReceiver;


public class Server {
	
	private final static int TIME_BETWEEN_MESSAGE = 5;
	private final static int DIST_BETWEEN_LINE_AND_CENTER = 65;
	private final static int MAX_SPEED_ROBOT = 100;

	

	public static void main(String[] args) throws IOException{
		

		Clock clock = new Clock();
		BroadcastManager broadcast;
		BroadcastReceiver receiver;

		LinkedList<VirtualRobot> listRobots = new LinkedList<>();	

		LinkedList<Information> listInformations = new LinkedList<>();
		
		//pas besoin pour vrai seulement temporaire
		RobotServerMes mesReceive = new RobotServerMes(50, 2, 10.0f, 50);

		
		SendServer2RobotsThread  communicationThread =new SendServer2RobotsThread(new ServerRobotMes(0, null),5);
		communicationThread.start();

		while(true) {
			
			broadcast = BroadcastManager.getInstance(9999);
			receiver = BroadcastReceiver.getInstance(8888);
			
			Message mes = new ServerRobotMes(clock.getTime(), null);

			broadcast.broadcast(mes.getByteBufferMessage());

			
			
			int indexList = 5;
			
			//look if got message
			if(indexList ==5) {
				//read the message
				VirtualRobot robotFromMes = new VirtualRobot(mesReceive.getPhysicalPosition(),mesReceive.getRobotId(),  mesReceive.getSpeed(), mesReceive.getTimestamp());
				
				//-1 means that it's not there
				if((indexList = isRobotInList(listRobots, robotFromMes)) == -1) {
					listRobots.addLast(robotFromMes);
				}
				else {
					listRobots.get(indexList).setLastTimeStamp(mesReceive.getTimestamp());
					listRobots.get(indexList).setPhysicalPosition(mesReceive.getPhysicalPosition());
					listRobots.get(indexList).setSpeed(mesReceive.getSpeed());
				}
	
			}
			
			
			
			//ANALYSE LA SITUATION ET DETERMINE LORDE DES ROBOTS
			
			
			
			listInformations.add(new Information(100, 2, 5));
			
			
			
			if(! communicationThread.isAlive()) {
				ServerRobotMes newMes = new ServerRobotMes(0, listInformations);
				communicationThread = new SendServer2RobotsThread(newMes,TIME_BETWEEN_MESSAGE);
				communicationThread.start();

			}

			//Trie la listRobots par la position physique des robots.
			Collections.sort(listRobots, new Comparator<VirtualRobot>() { 
				@Override 
				public int compare(VirtualRobot r1, VirtualRobot r2) { 
					return r1.getPhysicalPosition() - r2.getPhysicalPosition();
				} 
			});
			
			//ANALYSE LA SITUATION ET DETERMINE LORDE DES ROBOTS
			float timeToWaitBeforeCrossCenter=0.001f; //en seconde
			int distBeforeCenter=0; //en centimetre
			int newSpeed=0; //centimetre par seconde
			listInformations.clear();
			
			
			for(int i=0;i<listRobots.size();i++) {
				VirtualRobot thisRobot = listRobots.get(i);
				
				distBeforeCenter = DIST_BETWEEN_LINE_AND_CENTER-thisRobot.getPhysicalPosition();

				newSpeed = (int)(distBeforeCenter/timeToWaitBeforeCrossCenter);
				if(newSpeed>MAX_SPEED_ROBOT) newSpeed=MAX_SPEED_ROBOT;
				timeToWaitBeforeCrossCenter=distBeforeCenter/newSpeed+5;
				
				
				listInformations.add(new Information(thisRobot.getID(), i, newSpeed));				

			}
			if(! communicationThread.isAlive()) {
				ServerRobotMes newMes = new ServerRobotMes(0, listInformations);
				communicationThread = new SendServer2RobotsThread(newMes,TIME_BETWEEN_MESSAGE);
				communicationThread.start();
		
			}
				
			
		}
		
	}
	
	
	
	private static int isRobotInList(LinkedList<VirtualRobot> listRobots, VirtualRobot robotFromMes) {
		for(int i=0;i<listRobots.size();i++) {
			if (listRobots.get(i).getID() == robotFromMes.getID()){
				return i;
			}
		}
		return -1;
	}
	
}
