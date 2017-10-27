package fr.utbm.tr54.strategie;

import fr.utbm.tr54.tp1.MotorsController;
import fr.utbm.tr54.tp1.SensorsController;
import fr.utbm.tr54.tp1.Move;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;


public class FollowTheLineStupide implements Strategie  {
	
	@Override
	public Move nextStep(int detectedColor) {

		if(detectedColor == Color.BLACK) {
			return new Move(1,0);
		}
		else if(detectedColor == Color.WHITE) {
			return new  Move(0,2);
		}
		else {
			return new  Move(1,1);
		}
	}


	@Override
	public Move nextStep() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Move nextStep(float distance) {
		// TODO Auto-generated method stub
		return null;
	}

}
