package uniWork.f1app.Exceptions;

public class DriverContractNotFoundException extends RuntimeException {

    public DriverContractNotFoundException(String id) {
        super("Cound not find driver contract " + id);
    }
}
