package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";
    public static final String STATUS = "status";
    public static final String DELIMITER = " ";

    private static final Scanner sc = new Scanner(System.in);
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int POSITION_SIZE = 2;

    public static String responseUserStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : " + START);
        System.out.println("> 게임 종료 : " + END);
        System.out.println("> 게임 점수 : " + STATUS);
        System.out.println("> 게임 이동 : " + MOVE + " source위치 target위치 - 예. move b2 b3");
        final String input = sc.nextLine();
        validateUserCommand(input);
        return input;
    }

    private static void validateUserCommand(final String input) {
        validateNullCheck(input);
        validateNotAllowStartCommand(input);
    }

    private static void validateNullCheck(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 게임 명령에 공백을 입력할 수 없습니다.");
        }
    }

    private static void validateNotAllowStartCommand(final String input) {
        if (!(input.equals(START) || input.equals(END))) {
            throw new IllegalArgumentException("[ERROR] start, end 이외의 문자는 입력할 수 없습니다.");
        }
    }

    public static String responseUserCommand() {
        String input = sc.nextLine();
        validateNullCheck(input);
        validateNotAllowCommand(input);
        if (input.contains(MOVE)) {
            List<String> moveCommand = Arrays.asList(input.split(DELIMITER));
            validateMoveCommand(moveCommand);
            input = calculateMoveCommand(moveCommand);
        }
        return input;
    }

    private static void validateNotAllowCommand(final String input) {
        if (!(input.equals(END) || input.contains(MOVE) || input.equals(STATUS))) {
            String message = String.format("[ERROR] %s, %s, %s 이외의 문자는 입력할 수 없습니다.", END, MOVE, STATUS);
            throw new IllegalArgumentException(message);
        }
    }

    private static void validateMoveCommand(final List<String> moveCommand) {
        validateMoveCommandFirstIsMove(moveCommand);
        validateMoveCommandSize(moveCommand);
        validateEachSize(moveCommand);
        validateDuplicatePosition(moveCommand);
    }

    private static void validateMoveCommandFirstIsMove(final List<String> moveCommand) {
        if (!moveCommand.get(MOVE_COMMAND_INDEX).equals(MOVE)) {
            throw new IllegalArgumentException("[ERROR] 게임 이동은 move source위치 target위치(예. move b2 b3) 형식으로 입력해주세요.");
        }
    }

    private static void validateMoveCommandSize(final List<String> moveCommand) {
        if (moveCommand.size() != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 게임 이동은 move source위치 target위치(예. move b2 b3) 형식으로 입력해주세요.");
        }
    }

    private static void validateEachSize(final List<String> moveCommand) {
        validateInputPositionSize(moveCommand, SOURCE_POSITION_INDEX);
        validateInputPositionSize(moveCommand, TARGET_POSITION_INDEX);
    }

    private static void validateInputPositionSize(final List<String> moveCommand, int index) {
        if (moveCommand.get(index).length() != POSITION_SIZE) {
            throw new IllegalArgumentException("[ERROR] 게임 이동은 move source위치 target위치(예. move b2 b3) 형식으로 입력해주세요.");
        }
    }

    private static void validateDuplicatePosition(final List<String> moveCommand) {
        if (moveCommand.get(SOURCE_POSITION_INDEX).equals(moveCommand.get(TARGET_POSITION_INDEX))) {
            throw new IllegalArgumentException("[ERROR] source위치와 target위치를 다르게 입력해주세요.");
        }
    }

    private static String calculateMoveCommand(final List<String> moveCommand) {
        return String.join(DELIMITER,
                moveCommand.subList(SOURCE_POSITION_INDEX, TARGET_POSITION_INDEX + 1));
    }
}
