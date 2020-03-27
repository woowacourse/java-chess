package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";

    public static List<String> receiveCommand() {
        try {
            String input = scanner.nextLine();
            return Arrays.asList(input.split(DELIMITER));
        } catch (NullPointerException e) {
            return receiveCommand();
        }
    }
}
