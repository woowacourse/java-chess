package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMAND_DELIMITER = " ";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> getUserCommand() {
        return Arrays.asList(SCANNER.nextLine().split(COMMAND_DELIMITER));
    }
}
