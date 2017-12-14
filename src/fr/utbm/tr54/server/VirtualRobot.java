package fr.utbm.tr54.server;

import java.sql.Time;

public class VirtualRobot {
	
	private int ID;
	private float physicalPosition;
	private float speed;
	private long lastTimeStamp;
	
	
	public VirtualRobot(float physicalPosition, int ID, float speed, long timeStamp) {

		this.physicalPosition = physicalPosition;
		this.ID = ID;
		this.speed = speed;
		this.lastTimeStamp =timeStamp;
	}
	
	
	
	//GET and SET
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public long getLastTimeStamp() {
		return lastTimeStamp;
	}
	public void setLastTimeStamp(long lastTimeStamp) {

		this.lastTimeStamp = lastTimeStamp;
	}

	public float getPhysicalPosition() {
		return physicalPosition;
	}

	public void setPhysicalPosition(float physicalPosition) {
		this.physicalPosition = physicalPosition;
	}
	

}
