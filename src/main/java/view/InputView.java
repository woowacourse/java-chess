package view;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputCommand() {
		return SCANNER.nextLine();
	}
}
