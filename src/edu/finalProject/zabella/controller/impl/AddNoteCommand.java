package edu.finalProject.zabella.controller.impl;

import edu.finalProject.zabella.controller.Command;
import edu.finalProject.zabella.entity.Note;
import edu.finalProject.zabella.logic.LogicProvider;
import edu.finalProject.zabella.logic.NotebookLogic;
import edu.finalProject.zabella.logic.NotebookLogicException;
import edu.finalProject.zabella.util.GenerateId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNoteCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String execute(String request) {
        String response = null;
        String[] params;
        Note newNote;

        final Pattern pattern_short = Pattern.compile("ADD\\ntitle=([^\\n]+)\\ncontent=([^\\n]+)\\ndate=(\\d{4}-\\d{2}-\\d{2})");
        final Pattern pattern_full = Pattern.compile("ADD\\nid=([\\d]+)\\ntitle=([^\\n]+)\\ncontent=([^\\n]+)\\ndate=(\\d{4}-\\d{2}-\\d{2})");
        try {

            // validate request
            Matcher matcher_short = pattern_short.matcher(request);
            Matcher matcher_full = pattern_full.matcher(request);
            if (!matcher_short.matches() && !matcher_full.matches()) {
                throw new CommandException("ADD command format is incorrect.");
            }
            params = request.split("\n");
            newNote = new Note();

            if(params.length == 5) {
                newNote.setId(Integer.parseInt(params[1].split("=")[1]));
                newNote.setTitle(params[2].split("=")[1]);
                newNote.setContent(params[3].split("=")[1]);
                newNote.setD(format.parse(params[4].split("=")[1]));
            }else {
                ;
                newNote.setId(GenerateId.getNextId());
                newNote.setTitle(params[1].split("=")[1]);
                newNote.setContent(params[2].split("=")[1]);
                newNote.setD(format.parse(params[3].split("=")[1]));
            }
            logic.add(newNote);
            response = "Note " + newNote.getContent() + " saved successfully.";
        } catch (NotebookLogicException e) {
            response = "Note not saved.";
        } catch (CommandException | ParseException e) {
            response = e.getMessage();
        }

        return response;
    }
}