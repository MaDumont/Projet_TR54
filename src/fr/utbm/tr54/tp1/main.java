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
	
	private static MotorManager motors;
	private static SensorManager sensors;
	private static Clock clock;
	private static Strategie  strategie;
	private	static BroadcastManager broadcast;
	private	static BroadcastReceiver receiver;
	private static boolean master;
	
	public static void main(String[] args) throws IOException{

		motors = new MotorManager();		
		sensors = new SensorManager();


		
		final int button = Button.waitForAnyPress();
		
		if(button == Button.ID_UP) {
			master = true;

			broadcast = BroadcastManager.getInstance(9999);
			receiver = BroadcastReceiver.getInstance(8888);
			
			broadcast();
		}
		else if (button == Button.ID_DOWN) {
			master = false;

			broadcast = BroadcastManager.getInstance(8888);
			receiver = BroadcastReceiver.getInstance(9999);

		}
		
		if(!master) {
			receiver.addListener(new BroadcastListener() {

				@Override
				public void onBroadcastReceived(ByteBuffer message) {
					LCD.clear();

					LCD.drawString("MESSAGE RECEIVED", 2, 2);
					LCD.refresh();

					LCD.drawString("MESSAGE RECEIVED", 0, 2);

				}
			});
		}

	}
	
	private static void broadcast() throws IOException {
		LCD.clear();

		LCD.refresh();

		LCD.drawString("Broadcasting time", 0, 2);

		byte[] message = new byte[5];
		ByteBuffer buffer = ByteBuffer.wrap(message);
		
		buffer.putFloat((float) 5.5);
		broadcast.broadcast(buffer);
		
		LCD.clear();
		LCD.drawString("BROADCASTING", 0, 2);
	}

}
