package chess.view;

import java.util.Scanner;

public class InputView {

	private final static Scanner scanner = new Scanner(System.in);

	public static void printCommandGuide() {
		System.out.println("체스 게임을 시작합니다.\n"
				+ "게임 시작은 start, 종료는 end 명령을 입력하세요.");
	}

	public static String requestCommand() {
		String answer = scanner.nextLine();
		InputValidator.validateCommand(answer);
		return answer;
	}
}
