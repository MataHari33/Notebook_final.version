package edu.finalProject.zabella.controller.impl;

import edu.finalProject.zabella.controller.Command;
import edu.finalProject.zabella.logic.LogicProvider;
import edu.finalProject.zabella.logic.NotebookLogic;
import edu.finalProject.zabella.logic.NotebookLogicException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FindNoteByDateCommand implements Command {
    final Pattern DATE_PATTERN = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$");

    public String execute(String request) {
        final LogicProvider logicProvider = LogicProvider.getInstance();
        final NotebookLogic logic = logicProvider.getNotebookLogic();

        String response = null;
        String[] params;

        try {
            params = request.split("\n");

            // validate request
            Matcher matcher = DATE_PATTERN.matcher(params[1]);
            if (!matcher.matches()) {
                response = "Data format is incorrect.";
            }
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-mm-dd");
            logic.find(format.parse(params[1].split("=")[1]));
            response = "Found note with date  " + format.parse(params[1].split("=")[1]) + " successfully.";
        } catch (NotebookLogicException e) {
            response = "Error occured when finding a note.";
        } catch (ParseException e) {
            response = "Date format parsing is incorrect.";
        }
        return response;
    }
}