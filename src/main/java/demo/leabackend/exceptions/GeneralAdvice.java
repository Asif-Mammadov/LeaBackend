package demo.leabackend.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralAdvice {
    @ResponseBody
    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String generalHandler(GeneralException ex) {
        return ex.getMessage();
    }

}
