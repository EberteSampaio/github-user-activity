package command.impl;

import client.ClientHttpConfiguration;
import command.Command;
import service.GetActivityUseCase;

public class FetchGithubActivityUserCommand implements Command {
    @Override
    public void execute() {
        var user = "EberteSampaio";
        var useCase = new GetActivityUseCase(
                new ClientHttpConfiguration()
        );

        useCase.search(user);
    }
}
