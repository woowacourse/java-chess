package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

	private final static Scanner scanner = new Scanner(System.in);
	private static final String COMMAND_GUIDE = "> 체스 게임을 시작합니다.\n"
			+ "> 게임 시작 : start\n"
			+ "> 게임 종료 : end\n"
			+ "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
	private static final String DELIMITER = " ";

	public static void printCommandGuide() {
		System.out.println(COMMAND_GUIDE);
	}

	public static List<String> requestCommand() {
		String answer = scanner.nextLine();
		InputValidator.validateCommand(answer);
		return Arrays.asList(answer.split(DELIMITER));
	}
}
