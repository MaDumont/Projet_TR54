package fr.utbm.tr54.tp1;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.*;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

public class SensorsController {
	private EV3UltrasonicSensor distanceSensor;
	private EV3ColorSensor colorSensor;
	
	private SampleProvider sampleProvider;
	
	public SensorsController() {
		distanceSensor = new EV3UltrasonicSensor(SensorPort.S2);
		colorSensor =  new EV3ColorSensor(SensorPort.S3);
		
		sampleProvider = distanceSensor.getDistanceMode();
		
	}
	

	public int distance() {
		
		float[] tableauValeur = new float[10];	
		
		sampleProvider.fetchSample(tableauValeur,0);
		     
		return(int) (100*tableauValeur[0]);
	}
	
	

	public int distance(int n) {
		float[] tableauValeur = new float[10];
		float sommeDistance= 0;
		
		
		for (int i =0; i <n; i++) {	
			sampleProvider.fetchSample(tableauValeur,0);
			sommeDistance += 100*tableauValeur[0];
		}
		
		int moyenneDistance = (int)(sommeDistance/n);
		
		return moyenneDistance;		
	}
	

	
	/**
	 * Gets the color.
	 *
	 * @return the color detected
	 */
	public int getColor() {
		return colorSensor.getColorID();
	}

}
