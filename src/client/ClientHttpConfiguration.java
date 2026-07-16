package client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttpConfiguration {
    private final int HTTP_OK = 200;

    public HttpResponse<String> get (String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(uri))
                .build();
        var response = client.send(httpRequest, BodyHandlers.ofString());

        if (this.HTTP_OK != response.statusCode()){
            throw new Exception("Erro ao buscar dados. URI={"+uri+"}");
        }

        return response;
    }
}
