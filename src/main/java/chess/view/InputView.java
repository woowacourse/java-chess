package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String readCommand() {
        System.out.printf("체스 게임을 시작합니다.%n게임 시작은 start, 종료는 end 명령을 입력하세요.%n");

        String input = SCANNER.nextLine();
        validateCommand(input);
        return input;
    }

    private void validateCommand(String input) {
        if (!"start".equals(input) && !"end".equals(input)) {
            throw new IllegalArgumentException("start 와 end 명령만 입력할 수 있습니다.");
        }
    }
}
