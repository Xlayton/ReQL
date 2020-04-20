package app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtil {
	private static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	private static String createRegex = "CREATE\\s+TABLE\\s+['\"]?(?<tablename>[^'\"]+)['\"]?\\s*\\(\\s*(?<rownames>\\s*(?:[^\\)\\,]+)\\s*(?:\\,[^\\)\\,]+)*)\\s*\\)\\s*:\\s*line\\sformat\\s\\/(?<linerule>[^\\/]+)\\/\\s+file\\s['\"](?<filepath>[^'\"]+)['\"]\\s*;";
	private static String selectRegex = "SELECT\\s+(?<selectedrows>(?:[^,\\s]+)(?:\\,\\s*[^,\\s]+)*)\\s+FROM\\s+(?<tablename>\\w+)\\s*;";
	
	public static boolean promptForCommand() {
		String command = "";
		try {
			System.out.println("Enter command:");
			while(true) {
				String inputString = inputReader.readLine();
				command += inputString + "\n";
				if(inputString.endsWith(";")) break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handleCommand(command.strip());
	}
	
	private static boolean handleCommand(String input) {
		if(input.matches(createRegex)) {
			Pattern createPattern = Pattern.compile(createRegex);
			Matcher createMatch = createPattern.matcher(input);
			createMatch.matches();
			String tableName = createMatch.group("tablename");
			List<String> rowNames = new ArrayList<String>(Arrays.asList(createMatch.group("rownames").split(",\\s*")));
			rowNames.forEach(name -> name.strip());
			String tableRule = createMatch.group("linerule");
			String filePath = createMatch.group("filepath");
			TableUtil.createTable(tableName, filePath, tableRule, rowNames);
			return true;
		} else if(input.matches(selectRegex)){
			System.out.println("Select time");
			return true;
		} else if(input.matches("quit\\s*;") || input.matches("exit\\s*;")) {
			System.out.println("Goodbye");
			return false;
		} else {
			System.out.println("Command doesn't match any known commands");
			return true;
		}
	}
}
