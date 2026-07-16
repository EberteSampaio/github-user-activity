package exceptions;

public class EventNotFoundException extends DomainException {
    public EventNotFoundException(String message) {
        super(message);
    }
}
