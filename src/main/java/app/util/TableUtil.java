package app.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.model.Row;
import app.model.Table;

public class TableUtil {

	public static List<Table> loadedTables = new ArrayList<Table>();

	public static void createTable(String tableName, String filePath, String tableRule, List<String> rowNames) {
		BufferedReader fileReader;
		try {
			fileReader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("File cannot be found");
			return;
		}
		List<Row> rows = new ArrayList<Row>();
		for (String rowName : rowNames) {
			rows.add(new Row(rowName, new ArrayList<String>()));
		}
		Table t = new Table(tableName, filePath, tableRule, rows);
		try {
			String lineContent;
			Pattern linePattern = Pattern.compile(t.getTableRule());
			int index = 0;
			while ((lineContent = fileReader.readLine()) != null) {
				index++;
				Matcher lineMatcher = linePattern.matcher(lineContent);
				if (lineMatcher.matches()) {
					if(lineMatcher.groupCount() < t.getRows().size()) {
						System.out.println("Too few groups for rows specified");
						return;
					}
					for (int i = 0; i < lineMatcher.groupCount(); i++) {
						t.writeEntry(lineMatcher.group(i+1), i);
					}
				} else {
					System.out.println("File line " + index + " does not match provided regex rule");
					return;
				}
			}
			fileReader.close();
		} catch (IOException e) {
			System.out.println("File cannot be read");
			return;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Too many groups for rows specified");
		}
		for(Table table : loadedTables) {
			if(table.getName().equals(t.getName())) {
				System.out.println("Table with name '" + t.getName() + "' already exists");
				return;
			}
		}
		loadedTables.add(t);
		System.out.println("Table Created");
	}
	
	public static String selectRowsFromTable(String tableName, String...rows) {
		Table selectedTable = null;
		for(Table table : loadedTables) {
			if(table.getName().equals(tableName)) {
				selectedTable = table;
				break;
			}
		}
		return selectedTable != null ? selectedTable.toString(rows) : "No table with name '" + tableName + "' has been created";
	}
	public static String selectAllRowsFromTable(String tableName) {
		Table selectedTable = null;
		for(Table table : loadedTables) {
			if(table.getName().equals(tableName)) {
				selectedTable = table;
				break;
			}
		}
		return selectedTable != null ? selectedTable.toString() : "No table with name '" + tableName + "' has been created";
	}
}
