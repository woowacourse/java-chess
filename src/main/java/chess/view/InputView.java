package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = " ";

    public static List<String> InputString() {
        String input = SCANNER.nextLine();

        return Arrays.asList(input.split(DELIMITER));
    }
}
