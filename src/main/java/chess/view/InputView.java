package chess.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String DELIMITER = " ";
    public static final int LIMIT = -1;

    public static List<String> readCommand() {
        return Arrays.stream(scanner.nextLine().split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
