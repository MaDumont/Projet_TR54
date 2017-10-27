package fr.utbm.tr54.strategie;

import fr.utbm.tr54.tp1.Move;

public class FollowYourFriend2Points implements Strategie{

	private int a;
	private int d;	
	private int lastSpeed =0;
	private int cycleTime;

	public FollowYourFriend2Points(int a, int d, int cycleTime) {
		this.a = a;
		this.d = d;
		this.cycleTime = cycleTime;
	}
	public FollowYourFriend2Points() {
		this.a = 2;
		this.d = 10;
		this.cycleTime = 1;
	}

	@Override
	public Move nextStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move nextStep(int value) {
		try {
			Thread.sleep(cycleTime*100);
		}catch(InterruptedException e) {}

		int speed = (int) Math.min(Math.max(2.5* (value-20), Math.min(Math.max(a*(value-d), 0), lastSpeed)),50);

		return new Move(speed,speed);
	}

	@Override
	public Move nextStep(float distance) {
		// TODO Auto-generated method stub
		return null;
	}

}
