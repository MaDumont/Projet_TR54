package server;

import java.sql.Time;

public class VirtualRobot {
	
	private int ID;
	private int physicalPosition;
	private float speed;
	private Time lastTimeStamp;
	
	
	public VirtualRobot(int physicalPosition, int ID, float speed, Time timeStamp) {
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
	public Time getLastTimeStamp() {
		return lastTimeStamp;
	}
	public void setLastTimeStamp(Time lastTimeStamp) {
		this.lastTimeStamp = lastTimeStamp;
	}

	public int getPhysicalPosition() {
		return physicalPosition;
	}

	public void setPhysicalPosition(int physicalPosition) {
		this.physicalPosition = physicalPosition;
	}
	

}
