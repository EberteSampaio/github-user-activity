package command.impl;

import client.ClientHttpConfiguration;
import command.Command;
import domain.Event;
import service.GetActivityUseCase;
import utils.GitHubActivityFormatter;

import java.util.List;


public class FetchGithubActivityUserCommand implements Command {
    @Override
    public void execute() throws Exception {
        var user = "EberteSampaio";
        var useCase = new GetActivityUseCase(
                new ClientHttpConfiguration()
        );

        List<Event> events = useCase.search(user);

        events.forEach(e -> System.out.println(new GitHubActivityFormatter().format(e)));
    }
}
