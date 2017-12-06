package Threads;
import fr.utbm.tr54.message.SeverRobotMes;

public class SendServer2RobotsThread  extends Thread{
	private SeverRobotMes message;
	private int timeWaiting;
	
	public SendServer2RobotsThread(SeverRobotMes msg, int timeWaiting) {
		this.message = msg;
		this.timeWaiting = timeWaiting;
	}
	
	
	public void run() {
		
			
		try {
			Thread.sleep(timeWaiting);
		} catch (InterruptedException e) {}
			
	}

}
