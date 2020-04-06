package chess.view;

import java.util.Scanner;

/**
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	private InputView() {
	}

	public static String inputCommand() {
		try {
			System.out.println("명령을 입력해주세요.");
			return scanner.nextLine();
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return inputCommand();
		}
	}


}
