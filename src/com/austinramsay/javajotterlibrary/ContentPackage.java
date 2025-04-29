package com.austinramsay.javajotterlibrary;

import java.io.Serializable;
import java.util.ArrayList;

public class ContentPackage implements Serializable {
	private Integer lastNbId;
	private Integer lastNoteId;
	private ArrayList<Notebook> notebooks;
	private ArrayList<Note> notes;

	public ContentPackage() {
		notebooks = new ArrayList<Notebook>();
		notes = new ArrayList<Note>();
	}

	public void addNotebook(Notebook notebook) {
		notebooks.add(notebook);
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public ArrayList<Notebook> getNotebooks() {
		return notebooks;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public Integer getLastNbId() {
		return lastNbId;
	}

	public Integer getLastNoteId() {
		return lastNoteId;
	}

	public void setLastNbId(Integer id) {
		this.lastNbId = id;
	}

	public void setLastNoteId(Integer id) {
		this.lastNoteId = id;
	}
}