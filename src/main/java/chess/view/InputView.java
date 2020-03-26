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
		System.out.println("명령을 입력해주세요.");
		return scanner.nextLine().split(DELIMITER, LIMIT);
	}
}
