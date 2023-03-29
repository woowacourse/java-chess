package chess.view.input;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = " ";
    private static final int LIMIT = -1;

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public List<String> readCommands() {
        final String input = scanner.nextLine();

        return Arrays.stream(input.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
