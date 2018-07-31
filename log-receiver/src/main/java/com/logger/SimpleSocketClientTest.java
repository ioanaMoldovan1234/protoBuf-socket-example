package com.logger;

import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.logger.LogDefinition.Log;

/**
 * This class was used as a simple proof of concept for java socket client.
 *
 */
public class SimpleSocketClientTest {

	// public static void main(String args[]) {
	// while (true) {
	// // waits for message
	// receiveMessage();
	// }
	// }

	public static void receiveMessage() {

		String host = "127.0.0.1";
		int port = 8000;

		try {
			Socket client = new Socket(host, port);

			// Send message to Server
			// byte[] result = log.toByteArray() ;
			// client.getOutputStream().write(result);

			// Receive message from Server
			InputStream input = client.getInputStream();

			byte[] msg = recvMsg(input);
			Log receivedLog = Log.parseFrom(msg);

			System.out.println(receivedLog.getTimestamp());
			System.out.println(receivedLog.getUserId());
			System.out.println(receivedLog.getEvent());

			input.close();
			client.close();
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException:" + e.toString());
		} catch (java.io.IOException e) {
			System.out.println("IOException :" + e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Receive message from Server
	 * 
	 * @return
	 */
	public static byte[] recvMsg(InputStream inpustream) {
		try {

			byte len[] = new byte[1024];
			int count = inpustream.read(len);

			byte[] temp = new byte[count];
			for (int i = 0; i < count; i++) {
				temp[i] = len[i];
			}
			return temp;
		} catch (Exception e) {
			System.out.println("recvMsg() occur exception!" + e.toString());
		}
		return null;
	}

}
