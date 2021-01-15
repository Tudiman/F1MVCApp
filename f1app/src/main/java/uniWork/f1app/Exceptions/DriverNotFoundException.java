package uniWork.f1app.Exceptions;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException(String id) {
        super("Cound not find driver " + id);
    }
}
