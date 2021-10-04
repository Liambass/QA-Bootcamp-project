package com.liambass.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Id not found")
public class IdNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -8118026033083362214L;
	
}
