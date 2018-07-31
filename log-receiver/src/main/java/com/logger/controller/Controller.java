package com.logger.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logger.dto.LogDto;
import com.logger.service.Service;

@RestController
public class Controller {

	@Autowired
	private Service service;

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public void receiveEvent(@RequestBody(required = true) @Valid LogDto dto) {
		service.receiveLog(dto);
	}
}
