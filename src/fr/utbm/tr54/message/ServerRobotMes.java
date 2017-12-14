package fr.utbm.tr54.message;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

public class ServerRobotMes implements Message {
	
	private long timeStamp;
	private LinkedList<Information> robotsInfo;
	
	public ServerRobotMes(long timeStamp, LinkedList<Information> robotsInfo){

		this.timeStamp = timeStamp;
		this.robotsInfo = robotsInfo;
	}
	
	public ServerRobotMes(ByteBuffer mes) {
		generateFromByteBufferMessage(mes);
	}
	
	public long getTimeStamp(){
		return this.timeStamp;
	}

	public LinkedList<Information> getRobotsInfo(){
		return this.robotsInfo;
	}
	
	@Override
	public String toString() {
		String str = timeStamp + ":";
		for (Information information : robotsInfo) {
			str += information.toString() + "|"; 
		}
		return str;
	}
	
	@Override
	public byte[] getByteMessage() {
		return this.toString().getBytes();
	}
	
	@Override
	public Message generateFromByteMessage(byte[] mes) {
		ByteBuffer b = ByteBuffer.wrap(mes);
		String str = new String(b.array());
		long timeStamp = Long.parseLong(str.split(":")[0]);
		str = str.split(":")[1];
		LinkedList<Information> robotsInfo = new LinkedList<Information>();
		for (String string : str.split("|")) {
			robotsInfo.add( new Information( Integer.parseInt(string.split(";")[0]), Integer.parseInt(string.split(";")[1]), Float.parseFloat(string.split(";")[2]) ));
		}
		return new ServerRobotMes(timeStamp,robotsInfo);
	}

	@Override
	public ByteBuffer getByteBufferMessage() {
		ByteBuffer buffer = ByteBuffer.wrap(this.getByteMessage());
		buffer.put(this.getByteMessage());
		return buffer;
	}

	@Override
	public Message generateFromByteBufferMessage(ByteBuffer mes) {
		byte[] data = new byte[mes.capacity()];
		((ByteBuffer) mes.duplicate().clear()).get(data);
		Message message = this.generateFromByteMessage(data);
		return message;
	}
}
