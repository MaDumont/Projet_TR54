package server;

public class Information {
	
	private int idRobot;
	private int positionFifo;
	private float newSpeed;
	
	public Information(int idRobot, int positionFifo, int newSpeed) {
		this.idRobot=idRobot;
		this.positionFifo=positionFifo;
		this.newSpeed=newSpeed;
	}
	
	
	
	//GET and SET
	public int getIdRobot() {
		return idRobot;
	}
	public void setIdRobot(int idRobot) {
		this.idRobot = idRobot;
	}
	public int getPositionFifo() {
		return positionFifo;
	}
	public void setPositionFifo(int positionFifo) {
		this.positionFifo = positionFifo;
	}
	public float getNewSpeed() {
		return newSpeed;
	}
	public void setNewSpeed(float newSpeed) {
		this.newSpeed = newSpeed;
	}

}
