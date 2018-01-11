package fr.utbm.tr54.robot;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class SensorManager {
	private EV3IRSensor usSen;
	private EV3ColorSensor colSens;
	private SampleProvider s;
	
	public SensorManager(){
		usSen = new EV3IRSensor(SensorPort.S2);
		colSens = new EV3ColorSensor(SensorPort.S3);
		s = usSen.getDistanceMode();
	}
	
	float distance() {
		float range[] = new float[1];
		SampleProvider s = usSen.getDistanceMode();
		s.fetchSample(range, 0);
		if(range[0] < 0) {
			return 500;
		}
		return range[0];		     
	}
	
	public int captCouleur() {
		return colSens.getColorID();
	}
}
