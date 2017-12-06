package fr.utbm.tr54.strategie;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.SensorPort;

public class Controller {
	private EV3LargeRegulatedMotor left;
	private EV3LargeRegulatedMotor right;
	private EV3UltrasonicSensor usSen;
	private EV3ColorSensor colSens;
	private static final int DISTANCE_ROUE = 180;
	private static final int DIAMETRE_ROUE = 30;

	public Controller() {
		left = new EV3LargeRegulatedMotor(MotorPort.B);
		right = new EV3LargeRegulatedMotor(MotorPort.C);
		usSen = new EV3UltrasonicSensor(SensorPort.S2);
		colSens = new EV3ColorSensor(SensorPort.S3);
	}

	void forward() {
		left.setSpeed(360);
		right.setSpeed(360);
		left.forward();
		right.forward();
	}

	void forwardLeft() {
		left.setSpeed(100);
		right.setSpeed(1000);
		left.forward();
		right.forward();
	}

	void forwardRight() {
		left.setSpeed(1000);
		right.setSpeed(100);
		left.forward();
		right.forward();
	}

	void stop() {
		left.setSpeed(0);
		right.setSpeed(0);
		left.stop();
		right.stop();
	}

	float distance() {
		float range[] = new float[1];
		if (!usSen.isEnabled())
			usSen.enable();
		SampleProvider s = usSen.getDistanceMode();
		s.fetchSample(range, 0);
		usSen.disable();
		System.out.println(range[0]);
		return range[0];
	}

	public void rotate(float angleRadian) {
		int angleDegre = (int) (angleRadian * DISTANCE_ROUE * 360 / (DIAMETRE_ROUE * Math.PI * 2));
		right.rotate(-angleDegre, true);
		left.rotate(angleDegre);
	}

	public float[] captCouleur() {
		float[] color = new float[3];
		SampleProvider sp = colSens.getRGBMode();
		sp.fetchSample(color, 0);
		LCD.drawString("v1=" + color[0], 0, 0);
		LCD.drawString("v2=" + color[1], 0, 1);
		LCD.drawString("v3=" + color[2], 0, 2);

		return color;
	}

	public void runOnTrace() {
		
		
		int sp = colSens.getColorID();
		while (true) {
			
			
			switch (sp)
			{
				
			case Color.WHITE:
				forwardRight();
				
				break;
				
			case Color.BLACK:
			// black
				forwardLeft();
			break;	
				
			case Color.RED:
				// black
				forward();
				
				break;
				
			case Color.BLUE:
				forward();
				break;
				
			default:
				forward();
					
			}
				
			sp = colSens.getColorID();
			//LCD.drawInt(sp,0,0);
			//System.out.println(sp);
			//tab= captCouleur();
		}
	}

	public String[] runAUnPoint(final float a,final float D){
		final float[] sample = new float[1];
		SampleProvider sampleProvider = usSen.getDistanceMode();
		sampleProvider.fetchSample(sample, 0);
		float pourcentageSpeed = (Math.max(Math.min(50, a *(sample[0] - D)), 0)) / 100;
		left.setSpeed(pourcentageSpeed * left.getMaxSpeed());
		right.setSpeed(pourcentageSpeed * right.getMaxSpeed());
		left.forward();
		right.forward();
		return new String[]{Float.toString(sample[0]),Float.toString(pourcentageSpeed) };
	}
	public void runToutOuRien()
	{
		final float[] sample = new float[1];
		SampleProvider sampleProvider = usSen.getDistanceMode();
		sampleProvider.fetchSample(sample, 0);
		
		float pourcentageSpeed = 0.0f;
		if (sample[0] >= 35)
		{
			
			pourcentageSpeed = 0.5f;
			left.setSpeed(pourcentageSpeed * left.getMaxSpeed());
			right.setSpeed(pourcentageSpeed * right.getMaxSpeed());
			left.forward();
			right.forward();
		}
		else
		{
			this.stop();
			//this.forward();
		}
		//return new String[]{Float.toString(sample[0]),Float.toString(pourcentageSpeed) };
	}
}
