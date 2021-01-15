package uniWork.f1app.Exceptions;

public class ChampionshipRegistrationNotFoundException extends RuntimeException {

    public ChampionshipRegistrationNotFoundException(String id) {
        super("Cound not find championship registration " + id);
    }
}
