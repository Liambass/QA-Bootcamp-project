package com.liambass.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No matching records found")
public class NoMatchingRecordsException extends RuntimeException{

	private static final long serialVersionUID = 6219313210069201444L;

}