package view;

import java.util.Scanner;

public class InputView {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";
    public static final String STATUS = "status";
    public static final String DELIMITER = " ";
    public static final String EMPTY_STRING = "";
    public static final int SOURCE_INDEX = 0;
    public static final int TARGET_INDEX = 1;
    private static final int POSITIONS_COUNT = 2;
    private static final int POSITION_SIZE = 2;

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_COMMAND_INFORMATION = "> 게임 시작 : %s" + System.lineSeparator();
    private static final String END_COMMAND_INFORMATION = "> 게임 종료 : %s" + System.lineSeparator();
    private static final String MOVE_COMMAND_INFORMATION = "> 게임 이동 : %s source위치 target위치 - 예. move b2 b3" + System.lineSeparator();
    private static final String MOVE_COMMAND_FORM_ERROR_MESSAGE = "[ERROR] move source위치 target위치 형식으로 입력해주세요.";
    private static final String INPUT_POSITION_ERROR_MESSAGE = "[ERROR] source위치, target위치의 입력이 잘못되었습니다.";
    private static final String SAME_POSITION_ERROR_MESSAGE = "[ERROR] source위치, target위치가 같을 수 없습니다.";
    private static final String NULL_COMMAND_ERROR_MESSAGE = "[ERROR] 게임 명령에 공백을 입력할 수 없습니다.";
    private static final String START_COMMAND_ERROR_MESSAGE = "[ERROR] start, end 이외의 문자는 입력할 수 없습니다.";
    private static final String PLAY_COMMAND_ERROR_MESSAGE = "[ERROR] move, status, end 이외의 문자는 입력할 수 없습니다.";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String startCommand() {
        System.out.println(GAME_START_MESSAGE);
        System.out.printf(START_COMMAND_INFORMATION, START);
        System.out.printf(END_COMMAND_INFORMATION, END);
        System.out.printf(MOVE_COMMAND_INFORMATION, MOVE);
        String input = SCANNER.nextLine();
        validateStartCommand(input);
        return input;
    }

    public static String playCommand() {
        String input = SCANNER.nextLine();
        validatePlayCommand(input);
        if (input.contains(MOVE)) {
            input = input.replace(MOVE, EMPTY_STRING).trim();
            String[] inputs = input.split(DELIMITER);
            validateInputPositions(inputs);
            input = String.join(DELIMITER, inputs);
        }
        return input;
    }

    private static void validateInputPositions(final String[] input) {
        validateInputPositionsForm(input);
        validatePositionsSize(input);
        validateInputSamePosition(input);
    }

    private static void validateInputPositionsForm(final String[] input) {
        if (input.length != POSITIONS_COUNT) {
            throw new IllegalArgumentException(MOVE_COMMAND_FORM_ERROR_MESSAGE);
        }
    }

    private static void validatePositionsSize(final String[] input) {
        if (input[SOURCE_INDEX].length() != POSITION_SIZE
            || input[TARGET_INDEX].length() != POSITION_SIZE) {
            throw new IllegalArgumentException(INPUT_POSITION_ERROR_MESSAGE);
        }
    }

    private static void validateInputSamePosition(final String[] input) {
        if (input[SOURCE_INDEX].equals(input[TARGET_INDEX])) {
            throw new IllegalArgumentException(SAME_POSITION_ERROR_MESSAGE);
        }
    }

    private static void validatePlayCommand(final String input) {
        validateNullCheck(input);
        validateNotAllowPlayCommand(input);
    }

    private static void validateStartCommand(final String input) {
        validateNullCheck(input);
        validateNotAllowStartCommand(input);
    }

    private static void validateNullCheck(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(NULL_COMMAND_ERROR_MESSAGE);
        }
    }

    private static void validateNotAllowStartCommand(final String input) {
        if (!(input.equals(START) || input.equals(END))) {
            throw new IllegalArgumentException(START_COMMAND_ERROR_MESSAGE);
        }
    }

    private static void validateNotAllowPlayCommand(final String input) {
        if (!(input.contains(MOVE) || input.equals(END) || input.equals(STATUS))) {
            throw new IllegalArgumentException(PLAY_COMMAND_ERROR_MESSAGE);
        }
    }
}
