package fr.utbm.tr54.message;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Time;

public class RobotServerMes implements Message {
	
	private float physicalPosition;
	private int idRobot;
	private float speed;
	private long timeStamp;

	public RobotServerMes(float physicalPosition, int idRobot, float speed, long timeStamp) {
		this.physicalPosition = physicalPosition;
		this.idRobot = idRobot;
		this.speed = speed;
		this.timeStamp = timeStamp;
	}
	public RobotServerMes(ByteBuffer mes) {
		generateFromByteBufferMessage(mes);
	}
	
	public float getPhysicalPosition() {
		return this.physicalPosition;
	}
	
	public int getRobotId(){
		return this.idRobot;
	}

	public float getSpeed(){
		return this.speed;
	}
	
	public long getTimestamp(){
		return this.timeStamp;
	}
	
	@Override
	public String toString() {
		return this.physicalPosition + ";" + this.idRobot + ";" + this.speed + ";" + this.timeStamp;
	}

	@Override
	public byte[] getByteMessage() {
		return this.toString().getBytes();
	}
	
	@Override
	public ByteBuffer getByteBufferMessage() {
		ByteBuffer buffer = ByteBuffer.wrap(this.getByteMessage());
		buffer.put(this.getByteMessage());
		return buffer;
	}

	@Override
	public Message generateFromByteMessage(byte[] mes) {
		ByteBuffer b = ByteBuffer.wrap(mes);
		String str = new String(b.array());
		return new RobotServerMes(Float.parseFloat(str.split(";")[0]), Integer.parseInt(str.split(";")[1]),
				Float.parseFloat(str.split(";")[2]), Long.parseLong(str.split(";")[3]));
	}

	@Override
	public Message generateFromByteBufferMessage(ByteBuffer mes) {
		byte[] data = new byte[mes.capacity()];
		((ByteBuffer) mes.duplicate().clear()).get(data);
		Message message = this.generateFromByteMessage(data);
		return message;
	}
}
