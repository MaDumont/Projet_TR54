package fr.utbm.tr54.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import fr.utbm.tr54.message.Information;
import fr.utbm.tr54.message.RobotServerMes;
import fr.utbm.tr54.message.ServerRobotMes;
import fr.utbm.tr54.robot.MotorManager;
import fr.utbm.tr54.robot.SensorManager;
import fr.utbm.tr54.threads.SendRobot2ServerThread;
import fr.utbm.tr54.tp1.Clock;
import lejos.network.BroadcastListener;
import lejos.network.BroadcastManager;
import lejos.network.BroadcastReceiver;

public class CommTest {
	
	private static final int SAFE_ZONE_DISTANCE = 80;
	private static int id;
	private static float physicalPosition;
	private static float speed;
	private MotorManager motor;
	private SensorManager sensor;
	private boolean zoneConflict;
	private static BroadcastManager broadcast;
	private static BroadcastReceiver receiver;
	private static boolean asReceiveMessage;
	private static ServerRobotMes mesReceive;
	private static Clock clock;
	
	public static void main(String[] args) throws IOException{
		id = 1;
		physicalPosition = 5 ;
		speed = 10;
		clock = new Clock();
		asReceiveMessage = false;
		mesReceive = null;
		
		broadcast = BroadcastManager.getInstance(9999);
		RobotServerMes newMes = new RobotServerMes(physicalPosition,id,speed,clock.getTime());
		SendRobot2ServerThread  communicationThread =new SendRobot2ServerThread(newMes,0,broadcast);
		communicationThread.start();
		
		
		receiver = BroadcastReceiver.getInstance(8888);		
		receiver.addListener(new BroadcastListener() {

			@Override
			public void onBroadcastReceived(ByteBuffer message) {
	
				
				mesReceive = new ServerRobotMes(message);
				asReceiveMessage =true;
			}
		});
		
		if(! communicationThread.isAlive()) {
			newMes = new RobotServerMes(physicalPosition,id,speed,clock.getTime());
			communicationThread =new SendRobot2ServerThread(newMes,1,broadcast);
			communicationThread.start();

		}

	}
	
	
}