package lejos.network;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ListenerGroup implements BroadcastListener{
		
	private List<Float> vals = new ArrayList<>();
	private boolean available = false;
		
	
	@Override
	public void onBroadcastReceived(byte[] message) {
		// TODO Auto-generated method stub
		//val = ByteBuffer.wrap(message).getFloat();
		available = true;
	}
	
	
	/*public float getVal() {
		available = false;
		return val;
	}*/
	
	public boolean isAvailable() {
		return available;
	}

}
