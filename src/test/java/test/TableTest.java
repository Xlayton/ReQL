package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import app.model.Row;
import app.model.Table;

class TableTest {

	@Test
	public void should_create_table() {
		//arrange
		String tableName = "TableName";
		String filePath = "C:\\Path";
		String tableRule = "([^;]);(.*)";
		String[] rowNames = {"Row1", "Row2"};
		List<Row> rows = new ArrayList<Row>(Arrays.asList(new Row(rowNames[0], new ArrayList<String>()),new Row(rowNames[1], new ArrayList<String>())));
		//act
		Table table = new Table(tableName, filePath, tableRule, rows);
		//assert
		assertEquals(table.getName(), tableName);
		assertEquals(table.getFilePath(), filePath);
		assertEquals(table.getTableRule(), tableRule);
		for(int i = 0; i < table.getRows().size(); i++) {
			assertEquals(table.getRows().get(i).getName(), rowNames[i]);
		}
	}
	
	public void should_add_entry_to_target_row() {
		//arrange
		Table table = new Table("TableName", "C:\\Path", "([^;]);(.*)", new ArrayList<Row>(Arrays.asList(new Row("Row1", new ArrayList<String>()),new Row("Row2", new ArrayList<String>()))));
		String entryAddition = "Content";
		//act
		table.writeEntry(entryAddition, 1);
		//assert
		assertEquals(table.getRows().get(1).getEntries().get(0), entryAddition);
	}
}
