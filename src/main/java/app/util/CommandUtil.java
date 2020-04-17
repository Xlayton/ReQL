package app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandUtil {
	private static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static boolean promptForCommand() {
		String command = "";
		try {
			System.out.println("Enter command:");
			while(true) {
				String inputString = inputReader.readLine();
				command += inputString;
				if(inputString.endsWith(";")) break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handleCommand(command);
	}
	
	private static boolean handleCommand(String input) {
		System.out.println(input);
		return true;
	}
}
