package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readStartOrEnd() {
        System.out.println("체스 게임을 시작합니다.\n게임 시작은 start, 종료는 end 명령을 입력하세요.");
        String input = scanner.nextLine();
        validateEmpty(input);
        return input;
    }

    private void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 문지입니다.");
        }
    }
}
