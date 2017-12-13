package fr.utbm.tr54.message;

import java.nio.ByteBuffer;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SeverRobotMes implements Message {
	
	private Time timeStamp;
	private List<Information> robotsInfo;
	
	public SeverRobotMes(Time timeStamp, List<Information> robotsInfo){
		this.timeStamp = timeStamp;
		this.robotsInfo = robotsInfo;
	}
	
	public Time getTimeStamp(){
		return this.timeStamp;
	}

	public List<Information> getRobotsInfo(){
		return this.robotsInfo;
	}
	
	@Override
	public String toString() {
		String str = timeStamp.getTime() + ":";
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
		Time timeStamp = new Time(Long.parseLong(str.split(":")[0]));
		str = str.split(":")[1];
		List<Information> robotsInfo = new ArrayList<Information>();
		for (String string : str.split("|")) {
			robotsInfo.add( new Information( Integer.parseInt(string.split(";")[0]), Integer.parseInt(string.split(";")[1]), Float.parseFloat(string.split(";")[2]) ));
		}
		return new SeverRobotMes(timeStamp,robotsInfo);
	}
}
