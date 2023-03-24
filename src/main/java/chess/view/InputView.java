package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String REGEX = " ";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readCommand() {
        String input = scanner.nextLine();
        return splitInput(input);
    }

    private static List<String> splitInput(final String input) {
        return List.of(input.split(REGEX));
    }

    public static void terminate() {
        scanner.close();
    }
}
