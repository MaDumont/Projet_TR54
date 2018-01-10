package fr.utbm.tr54.robot;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import fr.utbm.tr54.message.*;
import fr.utbm.tr54.threads.SendRobot2ServerThread;
import fr.utbm.tr54.tp1.Clock;
import lejos.network.*;
import lejos.robotics.Color;

public class Robot {
	private static final int SAFE_ZONE_DISTANCE = 80;
	private final int id;
	private float physicalPosition;
	private float speed;
	private MotorManager motor;
	private SensorManager sensor;
	private boolean zoneConflict = false;
	private BroadcastManager broadcast;
	private BroadcastReceiver receiver;
	private ServerRobotMes mesReceive;
	private RobotServerMes newMes;
	private Clock clock;
	
	public Robot(int id, int physicalPosition, float speed) {
		this.id = id;
		this.setPhysicalPosition(physicalPosition);
		this.setSpeed(speed);
		motor = new MotorManager();
		sensor = new SensorManager();
		clock = new Clock();
		mesReceive = null;
	}

	
	public void runOnTrace() throws IOException {		

		//sender
		broadcast = BroadcastManager.getInstance(9999);
		SendRobot2ServerThread communicationThread = new SendRobot2ServerThread(null, 0, broadcast);

		//listener
		receiver = BroadcastReceiver.getInstance(8888);		
		receiver.addListener(new BroadcastListener() {

			@Override
			public void onBroadcastReceived(ByteBuffer message) {
				mesReceive = new ServerRobotMes(message);
				if(isZoneConflict()) {
					clock.syncTime(mesReceive.getTimeStamp());
					for(int i =0; i< mesReceive.getRobotsInfo().size();i++) {
						if(mesReceive.getRobotsInfo().get(i).getRobotId()== id) {
							speed = mesReceive.getRobotsInfo().get(i).getNewSpeed();
						}
					}
				}
			}
		});
		
		while (true) {
			
			if (isZoneConflict()){
				motor.setSpeed((int)speed);
				setPhysicalPosition(motor.CalculateDistance()/10);
				if(! communicationThread.isAlive()) {
					newMes = new RobotServerMes(this.physicalPosition,this.id,this.speed,this.clock.getTime());
					communicationThread =new SendRobot2ServerThread(newMes,1,broadcast);
					communicationThread.start();
				}
			}
			else {
				motor.setSpeed(motor.getMaxSpeed());
				motor.forward();
			}
			
			if((int)sensor.distance() < 30) {
				motor.stop();
			}
			else {
				switch (sensor.captCouleur()) {	
					case Color.WHITE:
						motor.forwardRight();
						break;
						
					case Color.BLACK:
						motor.forwardLeft();
						break;	
						
					case Color.RED:
						//send message to server to say I am in
						if(! communicationThread.isAlive()) {
							newMes = new RobotServerMes(0,this.id,this.speed,this.clock.getTime());
							communicationThread =new SendRobot2ServerThread(newMes,1,broadcast);
							communicationThread.start();
						}
						//Zone Conflict
						setZoneConflict(true);
						//reset the tachometer
						motor.resetTachometer();
						break;
					case Color.BLUE:
						//color blue 
						motor.forward();
						break;
						
					default:
						motor.forward();
				}
			}
			
			if (getPhysicalPosition() >= SAFE_ZONE_DISTANCE) {
				setZoneConflict(false);
				//send message to server to say I am outside now
				if(! communicationThread.isAlive()) {
					newMes = new RobotServerMes(1000000,this.id,this.speed,this.clock.getTime());
					communicationThread =new SendRobot2ServerThread(newMes,1,broadcast);
					communicationThread.start();
				}
			}	
		}
	}
	
	public int getId() {
		return id;
	}

	public float getPhysicalPosition() {
		return physicalPosition;
	}

	public void setPhysicalPosition(float physicalPosition) {
		this.physicalPosition = physicalPosition;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public boolean isZoneConflict() {
		return zoneConflict;
	}

	public void setZoneConflict(boolean zoneConflict) {
		this.zoneConflict = zoneConflict;
	}
	
	
	
}
