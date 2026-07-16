import command.CommandExecutor;
import command.impl.FetchGithubActivityUserCommand;

void main(String[] args) {
    try{
        if (args.length == 0){
            throw new RuntimeException("Username is required");
        }
        CommandExecutor commandExecutor = new CommandExecutor();

        commandExecutor.run(new FetchGithubActivityUserCommand(args[0]));
    }catch (RuntimeException e){
        System.err.println(e.getMessage());
    }

}
