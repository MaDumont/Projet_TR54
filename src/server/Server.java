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
		
		Clock clock = new Clock();
		BroadcastManager broadcast;
		BroadcastReceiver receiver;


		LinkedList<VirtualRobot> listRobots = new LinkedList<>();	
		
		RobotServerMes mesReceive = new RobotServerMes(50, 2, 10.0f, new Time(50));
		while(true) {
			
			
			int indexList = 0;
			
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
				
		}
		
	}
	
	
	
	private static int isRobotInList(LinkedList<VirtualRobot> listRobots, VirtualRobot robotFromMes) {
		for(int i=0;i<listRobots.size();i++) {
			if (listRobots.get(i).getID() == robotFromMes.getID()){
				return i;
			}
		}
		return -1;
		
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
