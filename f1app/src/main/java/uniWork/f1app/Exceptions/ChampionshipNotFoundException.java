package uniWork.f1app.Exceptions;

public class ChampionshipNotFoundException extends RuntimeException {

    public ChampionshipNotFoundException(String id) {
        super("Cound not find championship " + id);
    }
}
