import command.CommandExecutor;
import command.impl.FetchGithubActivityUserCommand;

void main(String[] args) {
    CommandExecutor commandExecutor = new CommandExecutor();

    commandExecutor.run(new FetchGithubActivityUserCommand(args[0]));

}
