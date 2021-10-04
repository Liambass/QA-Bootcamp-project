package com.liambass.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Range ends before it starts")
public class InvalidYearRangeException extends RuntimeException{

	private static final long serialVersionUID = 8448923997945300992L;

}
