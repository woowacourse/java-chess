package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String COMMAND_DELIMITER = " ";

    public List<String> readCommand() {
        return List.of(scanner.nextLine().trim().split(COMMAND_DELIMITER));
    }
}
