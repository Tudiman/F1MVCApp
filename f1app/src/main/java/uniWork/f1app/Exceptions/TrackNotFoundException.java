package uniWork.f1app.Exceptions;

public class TrackNotFoundException extends RuntimeException {

    public TrackNotFoundException(String id) {
        super("Cound not find track " + id);
    }
}
