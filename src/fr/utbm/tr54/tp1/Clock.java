package fr.utbm.tr54.tp1;

public class Clock {
	private static long initialTime;
	
	Clock(){
		initialTime = System.currentTimeMillis();
	}
	
	public long getTime() {
		return System.currentTimeMillis() - initialTime;
	}
	
	public void syncTime(long masterTime) {
		initialTime += masterTime - this.getTime();
	}
}
