package edu.finalProject.zabella.controller.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.finalProject.zabella.controller.Command;
import edu.finalProject.zabella.logic.LogicProvider;
import edu.finalProject.zabella.logic.NotebookLogic;
import edu.finalProject.zabella.logic.NotebookLogicException;

public class UpdateNoteCommand implements Command {
    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();
    final Pattern pattern = Pattern.compile("UPDATE\\nid=([\\d]+)\\ntitle=([^\\n]+)\\ncontent=([^\\n]+)\\ndate=(\\d{4}-\\d{2}-\\d{2})");
    final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

    @Override
    public String execute(String request) {
        String response = null;
        String[] params;
        try {

            // validate request
            Matcher matcher = pattern.matcher(request);
            if (!matcher.matches()) {
                throw new CommandException("Command format is incorrect.");
            }
            params = request.split("\n");
            int id = Integer.parseInt(params[1].split("=")[1]);
            String title = params[2].split("=")[1];
            String content = params[3].split("=")[1];
            Date date = formatter.parse(params[4].split("=")[1]);
            String[] params2change = Arrays.copyOfRange(params, 1, 5);
            if (!logic.update(id, params2change)) {
                response = "Note with ID " + Integer.parseInt(params[1].split("=")[1]) + " not found.";
            } else {
                response = "Note with ID " + Integer.parseInt(params[1].split("=")[1]) + " updated successfully.";
            }
        } catch (NotebookLogicException | CommandException e) {
            response = "Note doesn't updated successfully. Some troubles appeared.";
        } catch (ParseException e) {
            response = "Date format is wrong.";
        }
        return response;
    }
}
