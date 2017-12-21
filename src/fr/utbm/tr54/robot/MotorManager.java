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
	public MotorManager(int  speed){
		left = new EV3LargeRegulatedMotor(MotorPort.B);
		right = new EV3LargeRegulatedMotor(MotorPort.C);
		left.setSpeed(speed);
		right.setSpeed(speed);
	}
	public int getSpeed() {
		return speed;
	}
	public int getMaxSpeed() {
		return (int)((left.getMaxSpeed()+right.getMaxSpeed())/2);
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	void forward() {
		left.setSpeed(speed);
		right.setSpeed(speed);
		left.forward();
		right.forward();
	}
	void forwardLeft() {
		left.setSpeed(speed/10);
		right.setSpeed(speed);
		left.forward();
		right.forward();
	}
	void forwardRight() {
		left.setSpeed(speed);
		right.setSpeed(speed/10);
		left.forward();
		right.forward();
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
	public void resetTachometer()
	{
		left.resetTachoCount();
		right.resetTachoCount();
	}
}
