package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String BLANK = " ";

    private InputView() {
    }

    public static List<String> getCommand() {
        return Arrays.stream(scanner.nextLine().split(BLANK))
                .map(String::trim)
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());
    }
}
