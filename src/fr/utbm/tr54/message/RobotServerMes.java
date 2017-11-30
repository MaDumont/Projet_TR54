package fr.utbm.tr54.message;

import java.nio.ByteBuffer;
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
	public String toString() {
		return this.physicalPosition + ";" + this.idRobot + ";" + this.speed + ";" + this.timeStamp.getTime();
	}

	@Override
	public byte[] getByteMessage() {
		return this.toString().getBytes();
	}

	@Override
	public Message generateFromByteMessage(byte[] mes) {
		ByteBuffer b = ByteBuffer.wrap(mes);
		String str = new String(b.array());
		return new RobotServerMes(Integer.parseInt(str.split(";")[0]), Integer.parseInt(str.split(";")[1]),
				Float.parseFloat(str.split(";")[2]), new Time(Long.parseLong(str.split(";")[3])));
	}
}
