package exceptions;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException() {
        super("The user was not found on GitHub.");
    }
}
