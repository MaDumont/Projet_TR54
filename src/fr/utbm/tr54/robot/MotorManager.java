package fr.utbm.tr54.robot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorManager {
	private int speed;
	private EV3LargeRegulatedMotor left;
	private EV3LargeRegulatedMotor right;
	private static final int DISTANCE_ROUE = 180;
	private static final int DIAMETRE_ROUE = 30;
	
	public MotorManager(){
		left = new EV3LargeRegulatedMotor(MotorPort.B);
		right = new EV3LargeRegulatedMotor(MotorPort.C);
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	void forward() {
		left.setSpeed(speed);
		right.setSpeed(speed);
		left.backward();
		right.backward();
	}
	void forwardLeft() {
		left.setSpeed(speed-speed/2);
		right.setSpeed(speed);
		left.backward();
		right.backward();
	}
	void forwardRight() {
		left.setSpeed(speed);
		right.setSpeed(speed-speed/2);
		left.backward();
		right.backward();
	}
	void stop() {
		left.setSpeed(0);
		right.setSpeed(0);
		left.stop();
		right.stop();
	}
	public void rotate(float angleRadian) {
		int angleDegre = (int) (angleRadian * DISTANCE_ROUE * 360 / (DIAMETRE_ROUE * Math.PI * 2));
		right.rotate(-angleDegre, true);
		left.rotate(angleDegre);
	}
	//compute the distance
		public float CalculateDistance()
		{
					
			return (float) (Math.PI*DIAMETRE_ROUE*(left.getTachoCount()/360));
			
		}
}
