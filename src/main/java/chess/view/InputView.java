package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String END_COMMAND = "end";
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.\n> 게임 시작 : start\n> 게임 종료 : end\n> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String START = "start";
    private static final String INIT_INPUT_ERROR_MESSAGE = "[ERROR] 게임을 시작하려면 start를 입력해 주세요";
    private static final String INVALID_COMMAND_INPUT_ERROR = "[ERROR] 명령어를 다시 입력해 주세요.";
    private static final String BLANK = " ";
    private static final String EMPTY_INPUT_ERROR = "[ERROR] 명령어는 공백이 될 수 없습니다.";
    private static final String MOVE_COMMAND = "move";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int END_COMMAND_SIZE = 1;
    private static final int COMMAND_INDEX = 0;

    public static void printGameStartMessage() {
        printMessage(GAME_START_MESSAGE);
        readInitialGameCommand();
    }

    public static void readInitialGameCommand() {
        String input = SCANNER.nextLine();
        validateInitialCommand(input);
    }

    public static void validateInitialCommand(String input) {
        if (!input.equals(START)) {
            throw new IllegalArgumentException(INIT_INPUT_ERROR_MESSAGE);
        }
    }

    public static List<String> readPlayGameCommand() {
        String input = SCANNER.nextLine();
        validateBlank(input);
        return checkEndOrMove(input);
    }

    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR);
        }
    }

    private static List<String> checkEndOrMove(String input) {
        List<String> splitInput = Arrays.asList(input.split(BLANK));
        String command = splitInput.get(COMMAND_INDEX);
        if (isEnd(splitInput, command) || isMove(splitInput, command)) {
            return splitInput;
        }
        throw new IllegalArgumentException(INVALID_COMMAND_INPUT_ERROR);
    }

    private static boolean isMove(List<String> splitInput, String command) {
        return command.equals(MOVE_COMMAND) && splitInput.size() == MOVE_COMMAND_SIZE;
    }

    private static boolean isEnd(List<String> splitInput, String command) {
        return command.equals(END_COMMAND) && splitInput.size() == END_COMMAND_SIZE;
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
