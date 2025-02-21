package edu.finalProject.zabella.dao.impl;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.finalProject.zabella.dao.DaoException;
import edu.finalProject.zabella.dao.NoteBookDao;
import edu.finalProject.zabella.entity.Note;

public class FileNoteBookDao implements NoteBookDao {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final String filepath = "notebook.txt";

    public void save(Note n) throws DaoException {
        FileWriter pen = null;
        try {
            pen = new FileWriter(filepath, true);
            pen.write("Id: " + n.getId() + "\n");
            pen.write("Title: " + n.getTitle() + "\n");
            pen.write("Content: " + n.getContent() + "\n");
            if (n.getD() != null) {
                pen.write("Date: " + formatter.format(n.getD()) + "\n");
            }
            pen.write("******************************\n");
        } catch (IOException e) {
            throw new DaoException(e);
        } finally {
            if (pen != null) {
                try {
                    pen.close();
                } catch (IOException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    @Override
    public void save(List<String> lines) throws DaoException {
        try {

            writeFile(filepath, lines);
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    public static void writeFile(String filePath, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    @Override
    public List<Note> allNotes() throws DaoException {
        List<Note> notes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("notebook.txt"));
            Note note = new Note();

            String ln;
            while ((ln = reader.readLine()) != null) {
                if (ln.startsWith("Title: ")) {
                    note.setTitle(ln.substring(7));
                } else if (ln.startsWith("Content: ")) {
                    note.setContent(ln.substring(9));
                } else if (ln.startsWith("Id: ")) {
                    note.setId(Integer.parseInt(ln.substring(4)));

                } else if (ln.startsWith("Date: ")) {
                    if (!ln.substring(6).equals("null")) {
                        note.setD(formatter.parse(ln.substring(6)));
                    }
                } else if (ln.equals("******************************")) {
                    notes.add(note);
                }
            }
            return notes;
        } catch (ParseException e) {
            throw new DaoException("Error parse Date");
        } catch (IOException e) {
            throw new DaoException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Чтение файла построчно
    private static List<String> readFile(String filePath) throws DaoException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new DaoException(e);
        }
        return lines;
    }

    public List<String> allLines() throws DaoException {
        return readFile(filepath);
    }
}
