package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = "\\s+";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readGameCommand() {
        return List.of(scanner.nextLine().trim().split(DELIMITER, -1));
    }
}
