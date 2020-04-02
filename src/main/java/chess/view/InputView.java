package chess.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputCommand() {
		System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
		return SCANNER.nextLine();
	}
}
