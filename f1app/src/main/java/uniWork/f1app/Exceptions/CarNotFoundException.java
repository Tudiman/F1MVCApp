package uniWork.f1app.Exceptions;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String id) {
        super("Cound not find car " + id);
    }
}
