package server;

import java.io.IOException;
import java.nio.ByteBuffer;

import lejos.hardware.lcd.LCD;
import lejos.network.BroadcastListener;
import lejos.network.BroadcastManager;
import lejos.network.BroadcastReceiver;

public class Robot {
	
	private int distance;
	private float speed;
	private int id;
	private float lastTimeStamp;
	private static BroadcastManager broadcast;
	private static BroadcastReceiver receiver;
	

	public static void main(String[] args) throws IOException{
		LCD.clear();
		LCD.drawChar('S', 1, 1);
		LCD.drawChar('T', 2, 1);
		LCD.drawChar('A', 3, 1);
		LCD.drawChar('R', 4, 1);
		LCD.drawChar('T', 5, 1);
		LCD.drawChar('!', 6, 1);
		LCD.refresh();

		broadcast = BroadcastManager.getInstance(8888);
		receiver = BroadcastReceiver.getInstance(9999);
		
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
