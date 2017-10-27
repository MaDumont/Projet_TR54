package fr.utbm.tr54.tp1;

public class Move {
	
	private int leftMotorSpeed;
	private int rightMotorSpeed;
	
	
	public Move(int speedLeft, int speedRight) {
		this.rightMotorSpeed= speedRight;
		this.leftMotorSpeed = speedLeft;
	}
	
	
	public int getLeftMotorSpeed(){
		return leftMotorSpeed;
	}
	public int getRightMotorSpeed(){
		return rightMotorSpeed;
	}
	
	public void setLeftMotorSpeed(int speed){
		leftMotorSpeed=speed;
	}
	public void setRightMotorSpeed(int speed){
		rightMotorSpeed=speed;
	}
}
