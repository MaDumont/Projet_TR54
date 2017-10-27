package fr.utbm.tr54.tp1;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

import java.lang.Math;

public class MotorsController {
	
	private static final int DISTANCE_ROUE = 125;
	private static final int DIAMETRE_ROUE = 56;
	private static final int ONE_WHEEL_TURN =(int) (DISTANCE_ROUE*360/(DIAMETRE_ROUE*Math.PI*2));
	
	private EV3LargeRegulatedMotor motorRight;
	private EV3LargeRegulatedMotor motorLeft;

	
	MotorsController() {
		motorRight = new EV3LargeRegulatedMotor(MotorPort.C);
		motorLeft = new EV3LargeRegulatedMotor(MotorPort.B);
	}
	
	/**
	 * Forward.
	 * 
	 * This is the way to make go the robot forward.
	 * The robot not gonna stop in this method.
	 */
	public void forward() {
		motorRight.forward();
		motorLeft.forward();
	}
	
	/**
	 * Forward.
	 * This is the way to make go the robot forward during time ms.
	 * It's gonna stop after.
	 * 
	 * @param time the time
	 */
	public void forward(int time) {
		motorRight.forward();
		motorLeft.forward();
		try {
			Thread.sleep(100*time);
		}catch(InterruptedException e) {}
		stop();
		
	}
	
	/**
	 * Forward.
	 * 
	 * @param timeMotorRight the time motor right
	 * @param timeMotorLeft the time motor left
	 */
	public void forward(int timeMotorRight, int timeMotorLeft) {
		forwardRight(timeMotorRight);
		forwardRight(timeMotorRight);
	}
	
	/**
	 * Stop.
	 * 
	 * Stop all the motors.
	 */
	public void stop() {
		motorRight.stop();
		motorLeft.stop();
	}
	
	/**
	 * Sets the motors speed.
	 *
	 * @param speed the new motors speed
	 */
	public void setMotorsSpeed(int speed) {
		motorRight.setSpeed(speed*360/100);
		motorLeft.setSpeed(speed*360/100);
	}
	
	/**
	 * Sets the motors speed.
	 *
	 * @param speedMotorRight the speed motor right
	 * @param speedMotorLeft the speed motor left
	 */
	public void setMotorsSpeed(int speedMotorRight, int speedMotorLeft) {
		motorRight.setSpeed(speedMotorRight*360/100);
		motorLeft.setSpeed(speedMotorLeft*360/100);
	}
	
	public void setMotorsSpeed(Move newSpeed) {
		motorRight.setSpeed(newSpeed.getRightMotorSpeed()*360/100);
		motorLeft.setSpeed(newSpeed.getLeftMotorSpeed()*360/100);
	}

	/**
	 * Rotate.
	 *
	 * @param angleRadian the angle in radian
	 */
	public void rotate(float angleRadian) {
		int angleDegre = (int) (angleRadian*ONE_WHEEL_TURN); 
		motorRight.rotate(-angleDegre,true);
		motorLeft.rotate(angleDegre);
	}
		
	/**
	 * Forward right.
	 *
	 * @param time the time in ns  that the right motor turn
	 */
	public void forwardRight(int time)  {
		motorRight.stop();
		motorLeft.forward();
		try {
			Thread.sleep(time*100);
		}catch(InterruptedException e) {}
	}
	
	/**
	 * Forward left.
	 *
	 * @param time the time in ns that the left motor turn
	 */
	public void forwardLeft(int time) {
		motorLeft.stop();
		motorRight.forward();
		try {
			Thread.sleep(time*100);
		} catch (InterruptedException e) {	}
	}

}
