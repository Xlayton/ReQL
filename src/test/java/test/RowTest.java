package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import app.model.Row;

class RowTest {

	@Test
	public void should_create_row() {
		//arrange
		String name = "Test_Row";
		List<String> entries = new ArrayList<String>(Arrays.asList("Content1", "Content2", "Content3"));
		//act
		Row row = new Row(name, entries);
		//assert
		assertEquals(row.getName(), name);
		for(int i = 0; i < row.getEntries().size(); i++) {
			assertEquals(row.getEntries().get(i), entries.get(i));
		}
	}
	
	@Test
	public void should_add_entries() {
		//arrange
		Row row = new Row("Test_Row", new ArrayList<String>());
		String[] thingsToAdd = {"Content1", "Content2"};
		//act
		row.addEntry(thingsToAdd[0]);
		row.addEntry(thingsToAdd[1]);
		//assert
		for(int i = 0; i < row.getEntries().size(); i++) {
			assertEquals(row.getEntries().get(i), thingsToAdd[i]);
		}
		
	}

}
