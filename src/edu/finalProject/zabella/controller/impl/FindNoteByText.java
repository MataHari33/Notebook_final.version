package edu.finalProject.zabella.controller.impl;

import edu.finalProject.zabella.controller.Command;
import edu.finalProject.zabella.logic.LogicProvider;
import edu.finalProject.zabella.logic.NotebookLogic;
import edu.finalProject.zabella.logic.NotebookLogicException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindNoteByText implements Command {

    @Override
    public String execute(String request) {
        final LogicProvider logicProvider = LogicProvider.getInstance();
        final NotebookLogic logic = logicProvider.getNotebookLogic();
        final Pattern SYMBOLS_PATTERN = Pattern.compile("^[а-яА-ЯёЁ0-9 ]+$",  Pattern.CASE_INSENSITIVE);

        String response = null;
        String[] params;
        try {
            params = request.split("\n");

            // validate request
            Matcher matcher = SYMBOLS_PATTERN.matcher(request.split("\n")[1]);
            if (!matcher.matches()) {
                response = "Text format is incorrect.";
            }
            logic.findText(params[1].split("=")[1]);
            response = "Found note with text '" + params[1].split("=")[1] + "' successfully.";
        } catch (NotebookLogicException e) {
            response = "Error occured when finding a note.";
        }
        return response;
    }
}