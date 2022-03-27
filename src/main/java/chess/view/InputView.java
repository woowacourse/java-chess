package chess.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Pattern MOVE_PATTERN = Pattern.compile("move [a-h]\\d [a-h]\\d");

    public static String inputCommend() {
        return SCANNER.nextLine();
    }
}
