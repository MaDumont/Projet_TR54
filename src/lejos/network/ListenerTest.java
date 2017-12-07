package lejos.network;

import java.nio.ByteBuffer;

public class ListenerTest implements BroadcastListener{
	
	private float val = 0;
	private boolean available = false;
	
	@Override
	public void onBroadcastReceived(ByteBuffer message) {
		// TODO Auto-generated method stub
		//val = ByteBuffer.wrap(message).getFloat();
		available =true;
	}
	
	
	public float getVal() {
		available = false;
		return val;
	}
	
	public boolean isAvailable() {
		return available;
	}

}
