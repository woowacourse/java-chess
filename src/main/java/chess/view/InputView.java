package chess.view;

import java.util.Scanner;

/**
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String DELIMITER = " ";
	private static final int LIMIT = -1;

	public static String[] inputCommand() {
		try {
			System.out.println("명령을 입력해주세요.");
			String[] input = scanner.nextLine().split(DELIMITER, LIMIT);
			validateLength(input);
			return input;
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return inputCommand();
		}
	}

	private static void validateLength(String[] input) {
		if (input.length != 1 && input.length != 3) {
			throw new IllegalArgumentException("명령문의 길이가 1 또는 3이어야합니다.");
		}
	}
}
