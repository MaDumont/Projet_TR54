package Threads;
import communication.*;

public class SendRobot2ServerThread  extends Thread{
	private MsgRobot2Server message;
	private int timeWaiting;
	
	public SendRobot2ServerThread(MsgRobot2Server msg, int timeWaiting) {
		this.message = msg;
		this.timeWaiting = timeWaiting;
	}
	
	
	public void run() {
		
	
		System.out.println("thread : " + message.getIdRobot());
		
		try {
			Thread.sleep(timeWaiting);
		} catch (InterruptedException e) {}
			
	}

}
