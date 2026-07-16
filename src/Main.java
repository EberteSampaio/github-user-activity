import command.CommandExecutor;
import command.impl.FetchGithubActivityUserCommand;

void main() {
    CommandExecutor commandExecutor = new CommandExecutor();

    commandExecutor.run(new FetchGithubActivityUserCommand());

}
