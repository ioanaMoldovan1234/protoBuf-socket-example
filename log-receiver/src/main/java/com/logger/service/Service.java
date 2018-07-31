package com.logger.service;

import com.logger.dto.LogDto;

public interface Service {

	/**
	 * Receive a log event and send it forward to be saved.
	 * 
	 * @param logDto
	 *            data
	 */
	void receiveLog(LogDto logDto);
}
