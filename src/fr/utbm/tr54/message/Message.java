package fr.utbm.tr54.message;

public interface Message {

	public byte[] getByteMessage();
	public Message generateFromByteMessage(byte[] mes);
}
