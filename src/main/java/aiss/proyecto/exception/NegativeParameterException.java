package aiss.proyecto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason ="Negative parameter not accepted")
public class NegativeParameterException extends Exception{
}
