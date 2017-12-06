package Threads;

import fr.utbm.tr54.message.RobotServerMes;

public class SendRobot2ServerThread  extends Thread{
	private RobotServerMes message;
	private int timeWaiting;
	
	public SendRobot2ServerThread(RobotServerMes msg, int timeWaiting) {
		this.message = msg;
		this.timeWaiting = timeWaiting;
	}
	
	
	public void run() {
		
	
		System.out.println("thread : " + message.getRobotId());
		
		try {
			Thread.sleep(timeWaiting);
		} catch (InterruptedException e) {}
			
	}

}
