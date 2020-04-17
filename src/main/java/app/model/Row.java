package app.model;

import java.util.List;

public class Row {
	private String name;
	private List<String> entries;
	
	public Row(String name, List<String> entries) {
		this.name = name;
		this.entries = entries;
	}
	
	public void addEntry(String entry) {
		this.entries.add(entry);
	}

	public String getName() {
		return name;
	}

	public List<String> getEntries() {
		return entries;
	}
}
