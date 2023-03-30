package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readGameCommand() {
        String gameCommand = scanner.nextLine();
        validateNull(gameCommand);

        return Arrays.asList(gameCommand.split(DELIMITER));
    }

    public String readUserInput() {
        String gameCommand = scanner.nextLine();
        validateNull(gameCommand);

        return gameCommand.strip();
    }

    public int readRoomNumber() {
        return Integer.parseInt(scanner.nextLine());
    }

    private void validateNull(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백이 아니여야 합니다.");
        }
    }
}
