package base;

import java.util.ArrayList;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		this.folders = new ArrayList<>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
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

}
