package fr.utbm.tr54.robot;

import lejos.robotics.Color;

public class Robot {
	private final int id;
	private int physicalPosition;
	private float speed;
	private MotorManager motor;
	private SensorManager sensor;
	private boolean zoneConflict;
	
	public Robot(int id, int physicalPosition, float speed){
		this.id = id;
		this.setPhysicalPosition(physicalPosition);
		this.setSpeed(speed);
		motor = new MotorManager();
		sensor = new SensorManager();
	}

	public int getId() {
		return id;
	}

	public int getPhysicalPosition() {
		return physicalPosition;
	}

	public void setPhysicalPosition(int physicalPosition) {
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
	
	public void runOnTrace() {		
		int rgb = sensor.captCouleur();
	
		while (true) {
			//Receive info from server and update parameters
			
			//Send info to server if in conflict zone	
			if (isZoneConflict()){
				
			}
			
			switch (rgb)
			{			
			case Color.WHITE:
				motor.forwardRight();
				break;
				
			case Color.BLACK:
				motor.forwardLeft();
				break;	
				
			case Color.ORANGE:
				//Zone Conflict
				setZoneConflict(true);
				break;
					
			}
			rgb = sensor.captCouleur();
			//check if the robot is outside the conflict zone
			
		}
	}
	
}
