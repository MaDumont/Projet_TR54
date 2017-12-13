package fr.utbm.tr54.threads;
import fr.utbm.tr54.message.ServerRobotMes;

public class SendServer2RobotsThread  extends Thread{
	private ServerRobotMes message;
	private int timeWaiting;
	
	public SendServer2RobotsThread(ServerRobotMes msg, int timeWaiting) {

		this.message = msg;
		this.timeWaiting = timeWaiting;
	}
	
	
	public void run() {
		
			
		try {
			Thread.sleep(timeWaiting);
		} catch (InterruptedException e) {}
			
	}

}
