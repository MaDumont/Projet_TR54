package fr.utbm.tr54.tp1;

public class Clock {
	private static long initialTime;
	
	public Clock(){
		initialTime = System.currentTimeMillis();
	}
	
	public long getTime() {
		return System.currentTimeMillis() - initialTime;
	}
	
	public void syncTime(long masterTime) {
		initialTime = - masterTime +System.currentTimeMillis();
	}
}
