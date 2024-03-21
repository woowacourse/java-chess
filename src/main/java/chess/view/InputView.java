package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String SEPARATOR = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputCommand() {
        return List.of(scanner.nextLine().split(SEPARATOR));
    }
}
