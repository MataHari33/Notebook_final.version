package edu.finalProject.zabella.controller.impl;

import edu.finalProject.zabella.controller.Command;
import edu.finalProject.zabella.entity.Note;
import edu.finalProject.zabella.logic.LogicProvider;
import edu.finalProject.zabella.logic.NotebookLogic;
import edu.finalProject.zabella.logic.NotebookLogicException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindNoteByIDCommand implements Command {
    final Pattern ID_PATTERN = Pattern.compile("^\\d+$");

    public String execute(String request) {
        final LogicProvider logicProvider = LogicProvider.getInstance();
        final NotebookLogic logic = logicProvider.getNotebookLogic();

        String response = null;
        String[] params;

        params = request.split("\n");
        try {
            // validate request
            Matcher matcher = ID_PATTERN.matcher(params[1].split("=")[1]);
            if (!matcher.matches()) {
                throw new CommandException("ID format is incorrect.");
            }
            Note result = logic.find(params[1].split("=")[1]);
            if (result != null) {
                response = "Found note with ID " + Integer.parseInt(params[1].split("=")[1]) + " successfully.";
            } else {
                response = "Note with ID " + Integer.parseInt(params[1].split("=")[1]) + " not found.";
            }
        } catch (NotebookLogicException | CommandException e) {
            response = "Error occured when finding a note by ID.";
        }
        return response;
    }
}
