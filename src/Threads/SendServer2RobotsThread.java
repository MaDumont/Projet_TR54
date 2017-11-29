package Threads;
import communication.*;

public class SendServer2RobotsThread  extends Thread{
	private MsgServer2Robots message;
	private int timeWaiting;
	
	public SendServer2RobotsThread(MsgServer2Robots msg, int timeWaiting) {
		this.message = msg;
		this.timeWaiting = timeWaiting;
	}
	
	
	public void run() {
		
			
		try {
			Thread.sleep(timeWaiting);
		} catch (InterruptedException e) {}
			
	}

}
