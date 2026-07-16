package service;

import client.ClientHttpConfiguration;
import domain.Event;
import exceptions.DomainException;
import exceptions.EventNotFoundException;
import utils.MapperUtils;
import java.util.Arrays;
import java.util.List;

public class GetActivityUseCase {
    private final ClientHttpConfiguration httpConfiguration;
    private final String uri = "https://api.github.com/users/%s/events";
    private final int WITHOUT_EVENTS = 0;

    public GetActivityUseCase(ClientHttpConfiguration c){
        this.httpConfiguration = c;
    }

    public List<Event> search(String username) throws Exception {

        var response = this.httpConfiguration.get(String.format(this.uri, username));

        var eventsArray =MapperUtils.map(response.body(), Event[].class);
        List<Event> events = Arrays.asList(eventsArray);

        if (this.WITHOUT_EVENTS == events.size()){
            throw new EventNotFoundException("There are no events for this user.");
        }

        return events;
    }

}
