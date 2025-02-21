package edu.finalProject.zabella.logic.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import edu.finalProject.zabella.dao.DaoProvider;
import edu.finalProject.zabella.dao.NoteBookDao;
import edu.finalProject.zabella.dao.DaoException;
import edu.finalProject.zabella.entity.Note;
import edu.finalProject.zabella.logic.NotebookLogic;
import edu.finalProject.zabella.logic.NotebookLogicException;


public class NotebookLogicImpl implements NotebookLogic {
    private final DaoProvider provider = DaoProvider.getInstance();
    private final NoteBookDao dao = provider.getNoteBookDao();

    public void add(Note n) throws NotebookLogicException {
        try {
            dao.save(n);
        } catch (DaoException e) {
            throw new NotebookLogicException(e);
        }
    }

    public void add(String title, String content) throws NotebookLogicException {
        Note n = new Note(title, content);
        try {
            dao.save(n);
        } catch (DaoException e) {
            throw new NotebookLogicException(e);
        }
    }

    @Override
    public boolean update(int id, String title, String content, Date date) throws NotebookLogicException {
        Note n = find(id);
        try {
            if (n != null) {
                n.setTitle(title);
                n.setContent(content);
                n.setD(date);
                dao.save(n);
            }
            return true;
        } catch (DaoException e) {
            throw new NotebookLogicException(e);
        }
    }


    public boolean update(int id, String[] newData) throws NotebookLogicException {
        int startIndex = -1; // Начало набора данных
        int endIndex = -1; // Конец набора данных
        try {
            List<String> lines = dao.allLines();
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("Id:")) {
                    int currentId = Integer.parseInt(lines.get(i).split(": ")[1]);
                    if (currentId == id) {
                        startIndex = i;
                        // Ищем конец набора данных (до разделителя или конца файла)
                        for (int j = i + 1; j <= lines.size(); j++) {
                            if (j == lines.size() || lines.get(j).startsWith("******************************")) {
                                endIndex = j;
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            if (startIndex != -1 && endIndex != -1) {
                // Удаляем старый набор данных
                lines.subList(startIndex, endIndex).clear();
                // Вставляем новый набор данных
                for (String line : newData) {
                    lines.add(startIndex, line);
                    startIndex++;
                }
            }
            dao.save(lines);

        } catch (NumberFormatException | DaoException | IOException e) {
            throw new NotebookLogicException(e);
        }
        return true;
    }

    public Note find(String id) throws NotebookLogicException {
        Note result = new Note();
        try {
            List<Note> myNotes = dao.allNotes();
            for (Note n : myNotes) {
                if (id.equals(String.valueOf(n.getId()))) {
                    result = n;
                }
            }
        } catch (DaoException e) {
            throw new NotebookLogicException(e);
        }
        return result;
    }

    private boolean isTextInNote(Note n, String text) {
        return n.getTitle().contains(text) || n.getContent().contains(text);
    }


    public Note find(Date date) throws NotebookLogicException {
        Note result = new Note();
        try {
            List<Note> allNotes = dao.allNotes();
            for (Note note : allNotes) {
                if (note.getD() != null || note.getD().equals(date)) {
                    result = note;
                }
            }
        } catch (DaoException e) {
            throw new NotebookLogicException(e);
        }
        return result;
    }

    public Note find(int id) throws NotebookLogicException {
        Note result = new Note();
        try {
            List<Note> allNotes = dao.allNotes();
            for (Note note : allNotes) {
                if (Integer.valueOf(note.getId()) == id) {
                    result = note;
                }
            }
            return result;
        } catch (DaoException e) {
            throw new NotebookLogicException(e);
        }
    }

    public Note findText(String txt) throws NotebookLogicException {
        Note result = new Note();
        try {
            List<Note> allNotes = dao.allNotes();
            for (Note note : allNotes) {
                if (isTextInNote(note, txt)) {
                    result = note;
                }
            }
            return result;
        } catch (DaoException e) {
            throw new NotebookLogicException(e);
        }
    }

    public List<Note> allNotes() throws NotebookLogicException {
        try {
            return dao.allNotes();
        } catch (DaoException e) {
            throw new NotebookLogicException(e);
        }
    }
}
