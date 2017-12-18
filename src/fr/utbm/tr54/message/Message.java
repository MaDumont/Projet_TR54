package fr.utbm.tr54.message;

import java.nio.ByteBuffer;

public interface Message {

	public byte[] getByteMessage();
	public ByteBuffer getByteBufferMessage();
	public void generateFromByteMessage(byte[] mes);
	public void generateFromByteBufferMessage(ByteBuffer mes);
}
