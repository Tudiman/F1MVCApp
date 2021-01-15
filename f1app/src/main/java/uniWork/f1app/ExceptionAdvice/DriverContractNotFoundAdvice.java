package uniWork.f1app.ExceptionAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uniWork.f1app.Exceptions.DriverContractNotFoundException;

@ControllerAdvice
public class DriverContractNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DriverContractNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String driverContractNotFoundHandler(DriverContractNotFoundException ex) {
        return ex.getMessage();
    }
}
