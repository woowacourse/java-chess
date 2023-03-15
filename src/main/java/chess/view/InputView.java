package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readGameCommand() {
        String gameCommand = scanner.nextLine();
        validateNull(gameCommand);
        return gameCommand;
    }

    private void validateNull(String input) {
        if (input.isBlank() || input == null) {
            throw new IllegalArgumentException("공백이 아니여야 합니다.");
        }
    }
}
