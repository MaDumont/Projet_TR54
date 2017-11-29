package communication;

public class MsgRobot2Server implements Message {
	
	private int position;
	private float speed;
	private int idRobot;
	private float timeStamp;
	
	public MsgRobot2Server(int position, float speed, int idRobot, float timeStamp) {
		this.position=position;
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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
