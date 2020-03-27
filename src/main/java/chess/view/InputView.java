package chess.view;

import java.util.Scanner;

public class InputView {
	private static final int MOVE_COMMAND_LENGTH = 3;
	private static final int MOVE_COMMAND_INDEX = 0;
	private static final int SOURCE_INDEX = 1;
	private static final int TARGET_INDEX = 2;
	private static final String BLANK = " ";
	private static final String MOVE_COMMAND = "move";
	private static final String END_COMMAND = "end";
	public static final String START_COMMAND = "start";
	private static final String INPUT_MESSAGE_STATUS = "\n게임이 종료 되었습니다\n'status' 를 입력하여 결과를 확인하세요";
	private static final String INPUT_MESSAGE_GAME_START = "체스 게임을 시작합니다.\n게임 시작은 start, 종료 end 명령을 입력하세요";
	private static final String INPUT_MESSAGE_MOVE_COMMAND = "\n게임 이동 : move source위치 target위치 - 예 : move b2 b3";
	private static final String ERROR_MESSAGE_INPUT_GAME_START = "start 혹은 end 를 입력하세요";
	private static final String ERROR_MESSAGE_COMMAND_LENGTH = "형식이 잘못되었습니다 예 : move b2 b3";
	private static final String ERROR_MESSAGE_COMMAND_KEYWARD = "체스말의 움직이는 명령어의 시작은 move 입니다. 예 : move b2 b3";
	private static final String ERROR_MESSAGE_SAME_POSITION = "시작위치와 도착위치가 동일합니다.";

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
		return validateInputMoveCommand(scanner.nextLine());
	}

	private static String validateInputMoveCommand(String moveCommand) {
		String[] commands = moveCommand.split(BLANK);
		if (isIncorrectCommandLength(commands)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_COMMAND_LENGTH);
		}
		if (isNotMoveCommand(commands)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_COMMAND_KEYWARD);
		}
		if (isSourcePositionEqualTargetPosition(commands)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_SAME_POSITION);
		}
		return moveCommand;
	}

	private static boolean isIncorrectCommandLength(String[] commands) {
		return commands.length != MOVE_COMMAND_LENGTH;
	}

	private static boolean isNotMoveCommand(String[] commands) {
		return !MOVE_COMMAND.equals(commands[MOVE_COMMAND_INDEX]);
	}

	private static boolean isSourcePositionEqualTargetPosition(String[] commands) {
		return commands[SOURCE_INDEX].equals(commands[TARGET_INDEX]);
	}

	public static String inputStatus() {
		System.out.println(INPUT_MESSAGE_STATUS);
		return scanner.nextLine();
	}
}
