package fr.utbm.tr54.message;

import java.nio.ByteBuffer;

public interface Message {

	public byte[] getByteMessage();
	public ByteBuffer getByteBufferMessage();
	public Message generateFromByteMessage(byte[] mes);
	public Message generateFromByteBufferMessage(ByteBuffer mes);
}
