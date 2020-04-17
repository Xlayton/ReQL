package app;

import app.util.CommandUtil;

public class Application {
	public static void main(String[] args) {
		while(CommandUtil.promptForCommand()) {
			continue;
		}
	}
}
