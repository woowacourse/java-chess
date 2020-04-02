package chess.view;

import java.util.Scanner;

public class ConsoleInputView {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputChessCommand() {
		System.out.print(ConsoleOutputView.PROMPT);
		return SCANNER.nextLine();
	}

}
