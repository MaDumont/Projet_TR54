package communication;
import server.Information;


public class MsgServer2Robots implements Message{
	
	private float realTime;
	private Information[] arrayInfo;
	
	public MsgServer2Robots(float realTime, Information[] arrayInfo) {
		this.realTime=realTime;
		this.arrayInfo=arrayInfo;
	}
	
	public MsgServer2Robots(byte[] allMsg) {
		//To DO
		
	}
	

	@Override
	public byte[] messageToBytes() {
		// TODO Auto-generated method stub
		return null;
	}


	public float getRealTime() {
		return realTime;
	}


	public void setRealTime(float realTime) {
		this.realTime = realTime;
	}


	public Information[] getArrayInfo() {
		return arrayInfo;
	}


	public void setArrayInfo(Information[] arrayInfo) {
		this.arrayInfo = arrayInfo;
	}

}
