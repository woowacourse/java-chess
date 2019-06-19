package chess.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    public static Scanner SCANNER = new Scanner(System.in);
    private static final Pattern COMMAND_REGEX = Pattern.compile("(start|end|status|(move [a-h][1-8] [a-h][1-8]))");

    public static String command() {
        String input = "";
        do {
            input = SCANNER.nextLine();
        } while (!COMMAND_REGEX.matcher(input).matches());
        return input;
    }
}
