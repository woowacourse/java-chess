package chess.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = " ";
    private static final int LIMIT = -1;
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readCommand() {
        final String moveCommand = SCANNER.nextLine();

        return Arrays.stream(moveCommand.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
