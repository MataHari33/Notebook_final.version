package edu.finalProject.zabella.dao;

import java.io.IOException;
import java.util.List;

import edu.finalProject.zabella.entity.Note;

public interface NoteBookDao {

	void save(Note n) throws DaoException;

	void save(List<String> lines) throws DaoException, IOException;

	List<Note> allNotes() throws DaoException;

	List<String> allLines() throws DaoException;
}
