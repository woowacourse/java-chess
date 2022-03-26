package view;

import java.util.Scanner;

public class InputView {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";
    public static final String DELIMITER = " ";
    public static final String EMPTY_STRING = "";
    public static final int SOURCE_INDEX = 0;
    public static final int TARGET_INDEX = 1;
    private static final int POSITIONS_COUNT = 2;
    private static final int POSITION_SIZE = 2;
    private static final Scanner sc = new Scanner(System.in);

    public static String startCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : " + START);
        System.out.println("> 게임 종료 : " + END);
        System.out.println("> 게임 이동 : " + MOVE + " source위치 target위치 - 예. move b2 b3");
        String input = sc.nextLine();
        validateStartCommand(input);
        return input;
    }

    public static String playCommand() {
        String input = sc.nextLine();
        validatePlayCommand(input);
        if (input.contains(MOVE)) {
            input = input.replace(MOVE, EMPTY_STRING).trim();
            String[] inputs = input.split(DELIMITER);
            validateInputPositions(inputs);
            input = String.join(DELIMITER, inputs);
        }
        return input;
    }

    private static void validateInputPositions(String[] input) {
        validateInputPositionsForm(input);
        validatePositionsSize(input);
        validateInputSamePosition(input);
    }

    private static void validateInputPositionsForm(String[] input) {
        if (input.length != POSITIONS_COUNT) {
            throw new IllegalArgumentException("[ERROR] move source위치 target위치 형식으로 입력해주세요.");
        }
    }

    private static void validatePositionsSize(String[] input) {
        if (input[SOURCE_INDEX].length() != POSITION_SIZE
            || input[TARGET_INDEX].length() != POSITION_SIZE) {
            throw new IllegalArgumentException("[ERROR] source위치, target위치의 입력이 잘못되었습니다.");
        }
    }

    private static void validateInputSamePosition(String[] input) {
        if (input[SOURCE_INDEX].equals(input[TARGET_INDEX])) {
            throw new IllegalArgumentException("[ERROR] source위치, target위치가 같을 수 없습니다.");
        }
    }

    private static void validatePlayCommand(String input) {
        validateNullCheck(input);
        validateNotAllowPlayCommand(input);
    }

    private static void validateStartCommand(String input) {
        validateNullCheck(input);
        validateNotAllowStartCommand(input);
    }

    private static void validateNullCheck(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 게임 명령에 공백을 입력할 수 없습니다.");
        }
    }

    private static void validateNotAllowStartCommand(String input) {
        if (!(input.equals(START) || input.equals(END))) {
            throw new IllegalArgumentException("[ERROR] start, end 이외의 문자는 입력할 수 없습니다.");
        }
    }

    private static void validateNotAllowPlayCommand(String input) {
        if (!(input.contains(MOVE) || input.equals(END))) {
            throw new IllegalArgumentException("[ERROR] move, end 이외의 문자는 입력할 수 없습니다.");
        }
    }
}
