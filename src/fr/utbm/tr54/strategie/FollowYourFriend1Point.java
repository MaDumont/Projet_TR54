package fr.utbm.tr54.strategie;


import java.lang.Math.*;

import fr.utbm.tr54.tp1.Move;

public class FollowYourFriend1Point implements Strategie{


	private int a;
	private int d;	
	private int lastSpeed =0;
	private int cycleTime;

	public FollowYourFriend1Point(int a, int d, int cycleTime) {
		this.a = a;
		this.d = d;
		this.cycleTime = cycleTime;
	}
	public FollowYourFriend1Point() {
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
		
		int speed = Math.max(Math.min(50,a*(value-d)), 0);
		
		return new Move(speed,speed);
	}

	@Override
	public Move nextStep(float distance) {
		// TODO Auto-generated method stub
		return null;
	}

}
