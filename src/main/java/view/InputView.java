package view;

import java.util.Scanner;

public class InputView {

    public static final String START = "start";
    public static final String END = "end";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Command readStartOrEnd() {
        System.out.printf("체스 게임을 시작합니다.\n게임 시작은 %s, 종료는 %s 명령을 입력하세요.%n", START, END);
        String input = scanner.nextLine();
        validateEmpty(input);
        return Command.find(input);
    }

    private void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 문지입니다.");
        }
    }
}
