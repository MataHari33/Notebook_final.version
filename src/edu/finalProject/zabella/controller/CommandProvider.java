package edu.finalProject.zabella.controller;

import java.util.HashMap;
import java.util.Map;

import edu.finalProject.zabella.controller.impl.*;


public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.ADD, new AddNoteCommand());
        repository.put(CommandName.UPDATE, new UpdateNoteCommand());
        repository.put(CommandName.FINDBYDATE, new FindNoteByDateCommand());
        repository.put(CommandName.FINDBYID, new FindNoteByIDCommand());
        repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());
        repository.put(CommandName.FINDBYTEXT, new FindNoteByText());

    }

    public Command getCommand(String name) {//ADD
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            //write log
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }

}
