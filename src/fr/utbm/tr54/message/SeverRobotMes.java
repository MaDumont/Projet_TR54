package fr.utbm.tr54.message;

import java.sql.Time;
import java.util.List;

public class SeverRobotMes implements Message {
	
	private Time timeStamp;
	private List<Information> robotsInfo;
	
	public SeverRobotMes(Time timeStamp, List<Information> robotsInfo) {
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
	public byte[] getByteMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
