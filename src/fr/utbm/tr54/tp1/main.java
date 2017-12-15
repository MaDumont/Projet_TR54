package fr.utbm.tr54.tp1;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;

import fr.utbm.tr54.strategie.*;
import fr.utbm.tr54.robot.*;
import lejos.hardware.lcd.LCD;
import lejos.network.BroadcastListener;
import lejos.network.BroadcastManager;
import lejos.network.BroadcastReceiver;
import lejos.hardware.Button;

public class main {
	
	private static Robot robot;

	
	public static void main(String[] args) throws IOException{
		robot = new Robot(1,0,1000);
		
		robot.runOnTrace();
		


	}

}
