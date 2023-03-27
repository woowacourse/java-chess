package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    public static final int COMMAND_INDEX = 0;

    private static final String MOVE_DELIMITER = " ";
    private static final int MIN_COMMAND_SIZE = 1;
    private static final int MAX_COMMAND_SIZE = 3;

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n"
            + "> 게임 상황 : status");
    }

    public static List<String> readCommand() {
        String input = scanner.nextLine();
        validateEmpty(input);
        List<String> command = Arrays.stream(input.split(MOVE_DELIMITER))
            .collect(Collectors.toList());
        validateCommand(command);
        return command;
    }

    private static void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 문자입니다.");
        }
    }

    private static void validateCommand(List<String> command) {
        if (command.size() == MIN_COMMAND_SIZE || command.size() == MAX_COMMAND_SIZE) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
