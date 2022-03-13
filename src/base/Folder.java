package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.notes = new ArrayList<>();
		this.name = name;
	}
	
	public void addNote(Note note) {
		this.notes.add(note);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Note> getNotes() {
		return this.notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note note: this.notes) {
			if (note instanceof TextNote) nText++;
			else nImage++;
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int compareTo(Folder o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.getName());
	}
	
	
	public void sortNotes() {
		Collections.sort(this.notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		String[] args = keywords.split(" ");
		List<Note> list = new ArrayList<>();
		for (Note note : this.notes) {
			String title = note.getTitle();
			if (note instanceof TextNote) {
				String content = ((TextNote) note).getContent();
				title += " "+ content;
			}
			boolean contains = true;
			
			for (int i = 0; i < args.length ; i++) {
				if (args[i].equals("or")||args[i].equals("OR")) {
					i++; // Skip next since the prev is checked and contain the string
					continue;
				} else if (title.toUpperCase().contains(args[i].toUpperCase())) {
					// String found
					continue;
				} else {
					// If string is not match
					if (i+1<args.length && (args[i+1].equals("or")||args[i+1].equals("OR"))) {
						// The next string is or/OR, then directly check the next string
						i++;
						continue;
					} else {
						// The next is not contains
						contains = false;
						break;
					}
				}
			}
			if (contains) {
				list.add(note);
			}
		}
		return list;
	}
}
