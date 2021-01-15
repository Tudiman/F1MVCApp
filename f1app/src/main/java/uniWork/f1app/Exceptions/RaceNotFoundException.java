package uniWork.f1app.Exceptions;

public class RaceNotFoundException extends RuntimeException {

    public RaceNotFoundException(String id) {
        super("Cound not find race " + id);
    }
}
