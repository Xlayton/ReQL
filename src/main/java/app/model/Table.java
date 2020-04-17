package app.model;

import java.util.List;

public class Table {
	private String name;
	private String filePath;
	private String tableRule;
	private List<Row> rows;
	
	public Table(String name, String filePath, String tableRule, List<Row> rows) {
		
	}
	
	public void writeEntry(String entry, int targetRowIndex) {
		
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
	
	
}
