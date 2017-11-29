package communication;

public class MsgRobot2Server implements Message {
	
	private int distance;
	private float speed;
	private int idRobot;
	private float timeStamp;
	
	public MsgRobot2Server(int distance, float speed, int idRobot, float timeStamp) {
		this.distance=distance;
		this.speed=speed;
		this.idRobot=idRobot;
		this.timeStamp=timeStamp;
	}

	public MsgRobot2Server(byte[] allMsg) {
		//To DO
	}
	@Override
	public byte[] messageToBytes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//GET and SET

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getIdRobot() {
		return idRobot;
	}

	public void setIdRobot(int idRobot) {
		this.idRobot = idRobot;
	}

	public float getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(float timeStamp) {
		this.timeStamp = timeStamp;
	}

}
