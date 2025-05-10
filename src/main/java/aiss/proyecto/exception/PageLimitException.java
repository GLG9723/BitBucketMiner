package aiss.proyecto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason ="Page limit exceeded")
public class PageLimitException extends Exception{
    public PageLimitException(Integer limit, Integer limit2, String tip) {
        super("The maximum maxPages*n"+ tip + " you can request is".concat( limit.equals(limit2) ?
                ": "+String.valueOf(limit) : "between: "+String.valueOf(limit)+" and " + String.valueOf(limit2) ));
    }
}
