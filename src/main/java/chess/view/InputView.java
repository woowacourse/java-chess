package chess.view;

import java.util.Scanner;

public class InputView {

	private static final String BLANK_INPUT_INVALID_ERROR_MESSAGE = "공백을 입력할 수 없습니다.";
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String readCommand(){
		String input = SCANNER.nextLine();
		checkBlank(input);
		return input;
	}


	private static void checkBlank(String input) {
		if(input.isBlank()){
			throw new IllegalArgumentException(BLANK_INPUT_INVALID_ERROR_MESSAGE);
		}
	}
}
