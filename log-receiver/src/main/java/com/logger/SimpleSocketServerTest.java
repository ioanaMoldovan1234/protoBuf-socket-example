package com.logger;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import com.logger.LogDefinition.Log;

/**
 * 
 * This class was used as a simple proof of concept for java socket server.
 */
public class SimpleSocketServerTest {

	public static void sendMessage() throws Exception {
		ServerSocket listener = new ServerSocket(8000);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {

					Log log = Log.newBuilder().setTimestamp(1518609008L).setUserId(new Random().nextInt(6789008))
							.setEvent("event").build();
					// send message
					byte[] result = log.toByteArray();
					socket.getOutputStream().write(result);

					// PrintWriter out =
					// new PrintWriter(socket.getOutputStream(), true);
					// out.println(OffsetDateTime.now().toString());
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}

	// public static void main(String args[]) {
	// try {
	// while (true) {
	// System.out.println("Try to send message...");
	// Thread.sleep(1500);
	// sendMessage();
	// System.out.println("Message sent...");
	// }
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
