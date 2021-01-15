package uniWork.f1app.Exceptions;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String id) {
        super("Cound not find team " + id);
    }
}
