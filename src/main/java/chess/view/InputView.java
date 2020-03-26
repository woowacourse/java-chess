package chess.view;

import java.util.Scanner;

public class InputView {
	private static final String INPUT_MESSAGE_GAME_START = "체스 게임을 시작합니다.\n게임 시작은 start, 종료 end 명령을 입력하세요";
	private static final String INPUT_MESSAGE_MOVE_COMMAND = "\n게임 이동 : move source위치 tartget위치 - 예 : move b2 b3";
	private static final String ERROR_MESSAGE_INPUT_GAME_START = "start 혹은 end 를 입력하세요";
	public static final String START_COMMAND = "start";
	private static final String END_COMMAND = "end";

	private static final Scanner scanner = new Scanner(System.in);

	public static String inputGameState() {
		System.out.println(INPUT_MESSAGE_GAME_START);
		return validateInputGameState(scanner.nextLine());
	}

	private static String validateInputGameState(String gameState) {
		if (START_COMMAND.equalsIgnoreCase(gameState) || END_COMMAND.equalsIgnoreCase(gameState)) {
			return gameState;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_INPUT_GAME_START);
	}

	public static String inputMoveCommand() {
		System.out.println(INPUT_MESSAGE_MOVE_COMMAND);
		return scanner.nextLine();
	}
}
