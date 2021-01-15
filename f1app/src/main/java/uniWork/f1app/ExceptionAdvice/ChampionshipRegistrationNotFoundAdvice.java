package uniWork.f1app.ExceptionAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uniWork.f1app.Exceptions.ChampionshipRegistrationNotFoundException;

@ControllerAdvice
public class ChampionshipRegistrationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ChampionshipRegistrationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String championshipRegistrationNotFoundHandler(ChampionshipRegistrationNotFoundException ex) {
        return ex.getMessage();
    }
}
