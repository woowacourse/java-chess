package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void printStartMessage() {
        System.out.printf("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : %s\n"
                + "> 게임 종료 : %s\n"
                + "> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3\n", START, END, MOVE, MOVE);
    }

    public List<String> readInput() {
        String input = scanner.nextLine();
        validateEmpty(input);

        return Arrays.asList(input.split(" "));
    }

    private void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 문지입니다.");
        }
    }
}
