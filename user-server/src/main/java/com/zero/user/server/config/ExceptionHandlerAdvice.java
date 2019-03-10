package com.zero.user.server.config;

import com.zero.api.model.common.Result;
import com.zero.api.model.common.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result badRequestException(IllegalArgumentException exception) {
		return new Result(ResultCode.FAILURE, exception.getMessage());
	}
}
