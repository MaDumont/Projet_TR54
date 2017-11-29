package fr.utbm.tr54.message;

public class Information implements Message {
	
	private int IdRobot,orderList;
	private float newSpeed;

	public Information(int IdRobot, int orderList, float newSpeed) {
		this.IdRobot = IdRobot;
		this.orderList = orderList;
		this.newSpeed = newSpeed;
	}
	
	public int getRobotId(){
		return this.IdRobot;
	}
	
	public int getListOrder() {
		return this.orderList;
	}
	
	public float getNewSpeed() {
		return this.newSpeed;
	}

	@Override
	public byte[] getByteMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
