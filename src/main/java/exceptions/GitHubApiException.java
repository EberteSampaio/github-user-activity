package exceptions;

public class GitHubApiException extends DomainException {
    private int statusCode;
    private String body;
    public GitHubApiException(String message, int statusCode, String body) {
        super(message);
        this.statusCode = statusCode;
        this.body = body;
    }
}
