package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private static final String BLANK = " ";
    private static final String MOVE_COMMAND = "move";
    private static final String INPUT_MESSAGE_GAME_START = "체스 게임을 시작합니다.\n게임 시작은 start, 종료 end 명령을 입력하세요";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String INPUT_MESSAGE_MOVE_COMMAND = "\n게임 이동 : move source위치 target위치 - 예 : move b2 b3";

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputGameState() {
        System.out.println(INPUT_MESSAGE_GAME_START);
        return validateInputGameState(scanner.nextLine());
    }

    private static String validateInputGameState(String gameState) {
        if (START_COMMAND.equalsIgnoreCase(gameState) || END_COMMAND.equalsIgnoreCase(gameState)) {
            return gameState;
        }
        throw new IllegalArgumentException();
    }

    public static List<String> inputMoveCommand() {
        System.out.println(INPUT_MESSAGE_MOVE_COMMAND);
        return validateInputMoveCommand(scanner.nextLine());
    }

    private static List<String> validateInputMoveCommand(String input) {
        List<String> inputValues = Arrays.asList(input.split(BLANK));
        if (isIncorrectCommandLength(inputValues)) {
            throw new IllegalArgumentException(input + "커맨드는 move , 움직이고싶은말의 좌표 , 움직일좌표 형식에 맞지 않습니다.");
        }

        if (isNotMoveCommand(inputValues)) {
            throw new IllegalArgumentException(input + "커맨드는 move가 정확히 입려되지 않았습니다.");
        }

        if (isSourceEqualTarget(inputValues)) {
            throw new IllegalArgumentException(input + "커맨드는 움직이고싶은말의 좌표, 움직일좌표가 동일합니다. ");
        }
        return extractMovePosition(inputValues);
    }

    private static boolean isIncorrectCommandLength(List<String> inputValues) {
        return inputValues.size() != MOVE_COMMAND_SIZE;
    }

    private static boolean isNotMoveCommand(List<String> inputValues) {
        return !MOVE_COMMAND.equals(inputValues.get(MOVE_COMMAND_INDEX));
    }

    private static boolean isSourceEqualTarget(List<String> inputValues) {
        return inputValues.get(SOURCE_INDEX).equals(inputValues.get(TARGET_INDEX));
    }

    private static List<String> extractMovePosition(List<String> inputValues) {
        return Arrays.asList(inputValues.get(SOURCE_INDEX), inputValues.get(TARGET_INDEX));
    }
}
