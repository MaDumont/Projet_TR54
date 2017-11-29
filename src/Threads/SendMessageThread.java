package Threads;

public class SendMessageThread  extends Thread{
	
	public SendMessageThread(Message msg) {
		this.message = msg;
	}

}
