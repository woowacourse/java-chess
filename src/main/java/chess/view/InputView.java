package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = "\\s+";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readGameCommand() {
        final String input = scanner.nextLine();
        return List.of(input.trim().split(DELIMITER));
    }
}
