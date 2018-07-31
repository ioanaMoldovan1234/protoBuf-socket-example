package com.logger.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogDto {

	@Min(1)
	private long timestamp;

	@Min(0)
	private int userId;

	@NotBlank
	private String event;
}
