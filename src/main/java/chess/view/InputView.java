package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String readCommand() {
        String input = SCANNER.nextLine();
        validateCommand(input);
        return input;
    }

    private void validateCommand(String input) {
        if (!"start".equals(input) && !"end".equals(input) && !input.startsWith("move")) {
            throw new IllegalArgumentException("start, end, move 명령만 입력할 수 있습니다.");
        }
    }
}
