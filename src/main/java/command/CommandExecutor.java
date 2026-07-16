package command;

import exceptions.DomainException;

public class CommandExecutor {
    public void run(Command command){
        try {
            command.execute();
        }catch (DomainException e){
            System.err.println("Error: " + e.getMessage());
        }catch (Exception e){
            System.err.println("Critical internal error");
            e.printStackTrace();
        }
    }
}
