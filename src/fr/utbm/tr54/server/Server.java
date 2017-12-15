package fr.utbm.tr54.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import fr.utbm.tr54.message.*;
import fr.utbm.tr54.tp1.Clock;
import lejos.network.*;
import fr.utbm.tr54.threads.SendServer2RobotsThread;

public class Server {
	
	private final static int TIME_BETWEEN_MESSAGE = 5;
	private final static int DIST_BETWEEN_LINE_AND_CENTER = 65;
	private final static int MAX_SPEED_ROBOT = 100;
	private static boolean asMessages = false;
	private static RobotServerMes mesReceive = null;

	public static void main(String[] args) throws IOException{
		
		Clock clock = new Clock();
		BroadcastManager broadcast = BroadcastManager.getInstance(8888);
		BroadcastReceiver receiver = BroadcastReceiver.getInstance(9999);

			
		LinkedList<VirtualRobot> listRobots = new LinkedList<>();	
		LinkedList<Information> listInformations = new LinkedList<>();
		

		
		SendServer2RobotsThread  communicationThread =new SendServer2RobotsThread(new ServerRobotMes(clock.getTime(), null),0,broadcast);
		communicationThread.start();
					
		
		receiver.addListener(new BroadcastListener() {

			@Override
			public void onBroadcastReceived(ByteBuffer message) {
				asMessages = true;
				mesReceive = new RobotServerMes(message);
			}
		});
	


		
		while(true) {			
			
			//look if got message
			if(asMessages=true) {
				
				//read the message
				VirtualRobot robotFromMes = new VirtualRobot(mesReceive.getPhysicalPosition(),mesReceive.getRobotId(),  mesReceive.getSpeed(), mesReceive.getTimestamp());
				
				int indexList;
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

			//Trie la listRobots par la position physique des robots.
			Collections.sort(listRobots, new Comparator<VirtualRobot>() { 
				@Override 
				public int compare(VirtualRobot r1, VirtualRobot r2) { 
					return (int)(r1.getPhysicalPosition() - r2.getPhysicalPosition());
				} 
			});
			
			//ANALYSE LA SITUATION ET DETERMINE L'ORDE DES ROBOTS
			float timeToWaitBeforeCrossCenter=0.001f; //en seconde
			float distBeforeCenter=0.0f; //en centimetre
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
				ServerRobotMes newMes = new ServerRobotMes(clock.getTime(), listInformations);
				communicationThread = new SendServer2RobotsThread(newMes,TIME_BETWEEN_MESSAGE,broadcast);
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
