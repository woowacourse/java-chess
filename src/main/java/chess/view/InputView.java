package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String DELIMITER = " ";
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> inputCommand() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }
}
