package chess.view;

import chess.domain.game.Operations;

import java.util.Arrays;
import java.util.Scanner;

public class InputView {
	private static final String DELIMITER = " ";

	private static final Scanner SCANNER = new Scanner(System.in);

	public static Operations inputOperation() {
		String input = SCANNER.nextLine();
		return new Operations(Arrays.asList(input.split(DELIMITER)));
	}
}
