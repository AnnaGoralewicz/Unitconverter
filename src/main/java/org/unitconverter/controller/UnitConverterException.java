package org.unitconverter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "unit converter exception")
public class UnitConverterException extends RuntimeException{
}
