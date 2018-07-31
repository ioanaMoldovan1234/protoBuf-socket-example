package com.logger.service;

import java.io.IOException;
import java.net.Socket;

import org.springframework.stereotype.Component;

import com.logger.LogDefinition.Log;
import com.logger.dto.LogDto;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ServiceImpl implements Service {

	public static final int SOCKET_PORT = 8000;
	public static final String SOCKET_HOST = "127.0.0.1";

	@Override
	public void receiveLog(LogDto logDto) {
		try {
			sendMessage(logDto);
		} catch (IOException e) {
			log.error("Error when send message by socket:", e);
		}
	}

	/**
	 * Create a new socket on which is put the dto received.
	 * 
	 * @param logDto
	 *            message to be sent to the listening server socket.
	 * @throws IOException
	 */
	private void sendMessage(LogDto logDto) throws IOException {

		Socket socket = new Socket(SOCKET_HOST, SOCKET_PORT);

		try {

			// create proto buf object
			Log event = Log.newBuilder()
					.setTimestamp(logDto.getTimestamp())
					.setUserId(logDto.getUserId())
					.setEvent(logDto.getEvent())
					.build();
			// send message
			byte[] result = event.toByteArray();
			socket.getOutputStream().write(result);

		} finally {
			socket.close();
		}
	}
}
