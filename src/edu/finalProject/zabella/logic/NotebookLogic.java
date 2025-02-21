package edu.finalProject.zabella.logic;

import java.util.Date;
import java.util.List;

import edu.finalProject.zabella.entity.Note;

public interface NotebookLogic {

	public void add(Note n) throws NotebookLogicException;

	public void add(String title, String content) throws NotebookLogicException;

	public Note find(String text) throws NotebookLogicException;

	public Note find(Date date) throws NotebookLogicException;

	public Note findText(String s) throws NotebookLogicException;

	public List<Note> allNotes() throws NotebookLogicException;

	public boolean update(int id, String title, String content, Date date) throws NotebookLogicException;
	public boolean update(int id, String[] data) throws NotebookLogicException;

}
