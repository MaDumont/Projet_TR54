package fr.utbm.tr54.robot;

import java.io.IOException;
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
	private boolean zoneConflict;
	private BroadcastManager broadcast;
	private BroadcastReceiver receiver;
	private boolean asReceiveMessage;
	private ServerRobotMes mesReceive;
	private Clock clock;
	
	public Robot(int id, int physicalPosition, float speed){
		this.id = id;
		this.setPhysicalPosition(physicalPosition);
		this.setSpeed(speed);
		motor = new MotorManager();
		sensor = new SensorManager();
		clock = new Clock();
		asReceiveMessage = false;
		mesReceive = null;
	}

	
	public void runOnTrace() throws IOException {		
		motor.setSpeed((int)this.speed);
		motor.forward();
		
		broadcast = BroadcastManager.getInstance(8888);
		RobotServerMes newMes = new RobotServerMes(this.physicalPosition,this.id,this.speed,this.clock.getTime());
		SendRobot2ServerThread  communicationThread =new SendRobot2ServerThread(newMes,0,broadcast);
		communicationThread.start();
		
		
		/*receiver = BroadcastReceiver.getInstance(9999);		
		receiver.addListener(new BroadcastListener() {

			@Override
			public void onBroadcastReceived(ByteBuffer message) {
				asReceiveMessage =true;
				mesReceive = new ServerRobotMes(message);
			}
		});*/
	
		while (true) {
			
			
			//must do this before moving
			//Receive info from server and update parameters			
			if (isZoneConflict() && asReceiveMessage==true){
				this.clock.syncTime(mesReceive.getTimeStamp());
				adjustRobotWithInformationInList(mesReceive.getRobotsInfo());				
			}

			if(sensor.distance() < 10) {
				motor.setSpeed(0);
			}else {
				motor.setSpeed((int)this.speed);
			}
			switch (sensor.captCouleur())
			{			
			case Color.WHITE:
				motor.forwardRight();
				break;
				
			case Color.BLACK:
				motor.forwardLeft();
				break;	
				
			case Color.RED:
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
			
			if (getPhysicalPosition() >= SAFE_ZONE_DISTANCE)
			{
				setZoneConflict(false);
			}		
			
			//Must do this step after moving
			if (isZoneConflict()){
				setPhysicalPosition(motor.CalculateDistance()/10);
				
				if(! communicationThread.isAlive()) {
					newMes = new RobotServerMes(this.physicalPosition,this.id,this.speed,this.clock.getTime());
					communicationThread =new SendRobot2ServerThread(newMes,1,broadcast);
					communicationThread.start();

				}
			}
		}
	}
	
	private void adjustRobotWithInformationInList(LinkedList<Information> list) {
		for(int i =0; i< list.size();i++) {
			if(list.get(i).getRobotId()==this.id) {
				this.speed = list.get(i).getNewSpeed();
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
