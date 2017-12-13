package fr.utbm.tr54.robot;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class SensorManager {
	private EV3UltrasonicSensor usSen;
	private EV3ColorSensor colSens;
	
	public SensorManager(){
		usSen = new EV3UltrasonicSensor(SensorPort.S2);
		colSens = new EV3ColorSensor(SensorPort.S3);
	}
	
	float distance() {
		float range[] = new float[1];
		if (!usSen.isEnabled())
			usSen.enable();
		SampleProvider s = usSen.getDistanceMode();
		s.fetchSample(range, 0);
		usSen.disable();
		return range[0];
	}
	
	public int captCouleur() {
		return colSens.getColorID();
	}
}
