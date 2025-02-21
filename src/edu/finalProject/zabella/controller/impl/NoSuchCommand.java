package edu.finalProject.zabella.controller.impl;

import edu.finalProject.zabella.controller.Command;

public class NoSuchCommand implements Command {

    @Override
    public String execute(String request) {
        return "Request Error appeared.";
    }

}
