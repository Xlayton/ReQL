package app.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private String name;
	private String filePath;
	private String tableRule;
	private List<Row> rows;
	
	public Table(String name, String filePath, String tableRule, List<Row> rows) {
		this.name = name;
		this.filePath = filePath;
		this.tableRule = tableRule;
		this.rows = rows;
	}
	
	public void writeEntry(String entry, int targetRowIndex) {
		this.rows.get(targetRowIndex).addEntry(entry);
	}

	public String getName() {
		return name;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getTableRule() {
		return tableRule;
	}

	public List<Row> getRows() {
		return rows;
	}
	
	@Override
	public String toString() {
		String content = "" + this.name + "\n---------------\n";
		for(Row row : this.rows) {
			content += "" + row.getName() + "  ";
		}
		content += "\n";
		for(int i = 0; i < this.rows.get(0).getEntries().size(); i++) {
			for(Row row : this.rows) {
				content += "" + row.getEntries().get(i) + "  ";
			}
			content += "\n";
		}
		content += "\n---------------\n";
		return content;
	}
	
	public String toString(String...rows) {
		String content = "" + this.name + "\n---------------\n";
		boolean isMatch = false;
		List<Row> selectedRows = new ArrayList<Row>();
		for(int i = 0; i < rows.length; i++) {
			for(Row row : this.rows) {
				System.out.println("Row name: " + row.getName());
				System.out.println("Requested name: " + rows[i]);
				if(row.getName().equals(rows[i])) {
					isMatch = true;
					selectedRows.add(row);
					content += "" + row.getName() + "  ";
				}
			}
			if(!isMatch) {
				throw new IllegalArgumentException("No row with the name " + rows[i]);
			}
			isMatch = false;
		}
		content += "\n";
		for(int i = 0; i < selectedRows.get(0).getEntries().size(); i++) {
			for(Row row : selectedRows) {
				content += "" + row.getEntries().get(i) + "  ";
			}
			content += "\n";
		}
		content += "\n---------------\n";
		return content;
	}
	
	
}
