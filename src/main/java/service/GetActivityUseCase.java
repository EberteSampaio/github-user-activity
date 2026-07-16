package service;

import client.ClientHttpConfiguration;

public class GetActivityUseCase {
    private final ClientHttpConfiguration httpConfiguration;
    private final String uri = "https://api.github.com/users/%s/events";

    public GetActivityUseCase(ClientHttpConfiguration c){
        this.httpConfiguration = c;
    }

    public void search(String username){

        try {
            var response = this.httpConfiguration.get(String.format(this.uri, username));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mapper(String body){
        try(JsonReader )
    }

}
