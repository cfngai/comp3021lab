package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		this.folders = new ArrayList<>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders() {
		return this.folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		Folder folder = null;
		for (Folder _folder : folders) {
			if (_folder.getName().equals(folderName)) {
				folder = _folder; break;
			}
		}
		if (folder==null) {
			folder = new Folder(folderName); folders.add(folder);
		}
		for (Note _note : folder.getNotes()) {
			if (_note.equals(note)){
				System.out.println("Creating note "+ note.getTitle()+" under folder "+ folderName +" failed");
				return false;
			}
		}
		folder.addNote(note);
		return true;

	}
	
	public void sortFolders() {
		for (int i=0 ; i< this.folders.size(); i++) this.folders.get(i).sortNotes();
		Collections.sort(this.folders);
	}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> list = new ArrayList<>();
		for (Folder folder: this.folders) {
			for (Note note: folder.searchNotes(keywords)) list.add(note);
		}
		return list;
	}
	
}
