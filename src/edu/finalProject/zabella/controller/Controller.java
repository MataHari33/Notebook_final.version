package edu.finalProject.zabella.controller;

public class Controller {

    private final CommandProvider provider = new CommandProvider();

    public String doAction(String request) {
        char paramDelimeter = '\n';
        String commandName = request.substring(0, request.indexOf(paramDelimeter));
        Command executionCommand = provider.getCommand(commandName.toUpperCase());//ADD
        String response;
        try {
            response = executionCommand.execute(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
