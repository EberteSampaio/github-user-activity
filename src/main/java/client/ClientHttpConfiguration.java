package client;

import exceptions.GitHubApiException;
import exceptions.UserNotFoundException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class ClientHttpConfiguration {
    private final int HTTP_NOT_FOUND = 404;
    private final int LIMIT_TO_TIME_OUT = 20;

    public HttpResponse<String> get (String uri) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(this.LIMIT_TO_TIME_OUT))
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(uri))
                .header("X-GitHub-Api-Version", "2026-03-10")
                .build();
        var response = client.send(httpRequest, BodyHandlers.ofString());

        if (this.HTTP_NOT_FOUND == response.statusCode()){
            throw new UserNotFoundException();
        }

        if (response.statusCode() >= 400) {
            throw new GitHubApiException(
                    "Error communicating with the GitHub API.",
                    response.statusCode(),
                    response.body()
            );
        }

        return response;
    }
}
