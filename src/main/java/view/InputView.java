package view;

import controller.Command;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static void printStartMessage() {
        System.out.printf("> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : %s\n"
            + "> 게임 종료 : %s\n"
            + "> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3\n", Command.START, Command.END, Command.MOVE, Command.MOVE);
    }

    public static String readCommand() {
        String input = scanner.nextLine();
        validateEmpty(input);
        return input;
    }

    private static void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 문자입니다.");
        }
    }
}
