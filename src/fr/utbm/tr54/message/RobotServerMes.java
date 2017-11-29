package fr.utbm.tr54.message;

import java.sql.Time;

public class RobotServerMes implements Message {
	
	private int physicalPosition,idRobot;
	private float speed;
	private Time timeStamp;

	public RobotServerMes(int physicalPosition, int idRobot, float speed, Time timeStamp) {
		this.physicalPosition = physicalPosition;
		this.idRobot = idRobot;
		this.speed = speed;
		this.timeStamp =timeStamp;
	}
	
	public int getPhysicalPosition() {
		return this.physicalPosition;
	}
	
	public int getRobotId(){
		return this.idRobot;
	}

	public float getSpeed(){
		return this.speed;
	}
	
	public Time getTimestamp(){
		return this.timeStamp;
	}

	@Override
	public byte[] getByteMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
