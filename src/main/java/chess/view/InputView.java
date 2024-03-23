package chess.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String COMMAND_DELIMITER = " ";

    public static List<String> readCommand() {
        return List.of(scanner.nextLine().split(COMMAND_DELIMITER));
    }
}
