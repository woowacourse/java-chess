package view;

import java.util.Scanner;

public class InputView {
	public static final int COMMAND_INDEX = 0;
	public static final int SOURCE_POSITION = 1;
	public static final int TARGET_POSITION = 2;
	public static final String DELIMITER = " ";
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputCommand() {
		return SCANNER.nextLine();
	}
}
