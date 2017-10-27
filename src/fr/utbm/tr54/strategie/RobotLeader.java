package fr.utbm.tr54.strategie;

import fr.utbm.tr54.tp1.Move;

public class RobotLeader implements Strategie{

	//cyclingTime en milliseconde
	private int cyclingTime;
	private int avance = 0;
	
	public RobotLeader() {
		this.cyclingTime=500;
	}
	public RobotLeader(int cyclingTime) {
		this.cyclingTime=cyclingTime;
	}
	
	@Override
	public Move nextStep() {
		avance = (avance+1)%2;
		try {
			Thread.sleep(cyclingTime);
		}catch(InterruptedException e) {}
		
		if(avance>0) {
			return new Move(40,40);
		}else {
			return new Move(0,0);
		}
	}

	@Override
	public Move nextStep(int value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move nextStep(float distance) {
		// TODO Auto-generated method stub
		return null;
	}

}
