package chess.view;

import java.util.Scanner;

public class InputView {

	private static final String INVALID_INPUT = "start와 end만 가능합니다.";
	private static final String START = "start";
	private static final String END = "end";

	private static final Scanner scanner = new Scanner(System.in);

	public boolean askIfStart() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");

		String input = scanner.nextLine();
		validateInput(input);
		return input.equalsIgnoreCase(START);
	}

	private void validateInput(String input) {
		if (!input.equalsIgnoreCase(START) && !input.equalsIgnoreCase(END)) {
			throw new IllegalArgumentException(INVALID_INPUT);
		}
	}
}
