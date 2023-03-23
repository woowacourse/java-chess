package chessgame.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String COMMAND_DELIMITER = " ";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readCommand() {
        return List.of(scanner.nextLine().split(COMMAND_DELIMITER));
    }
}
