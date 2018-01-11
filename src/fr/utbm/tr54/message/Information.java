package fr.utbm.tr54.message;

public class Information {
	
	private int IdRobot,orderList;
	private float newSpeed;

	public Information(int IdRobot, int orderList, float newSpeed) {
		this.IdRobot = IdRobot;
		this.orderList = orderList;
		this.newSpeed = newSpeed;
	}
	
	/**
	 * @return get IdRobot
	 */
	public int getRobotId(){
		return this.IdRobot;
	}
	
	/**
	 * @return
	 */
	public int getListOrder() {
		return this.orderList;
	}
	
	/**
	 * @return
	 */
	public float getNewSpeed() {
		return this.newSpeed;
	}
	
	/**
	 * @return
	 */
	@Override
	public String toString() {
		return IdRobot + ";" + orderList + ";" + newSpeed;
	}
}
