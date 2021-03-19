package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final List<String> INIT_COMMANDS = Arrays.asList("start", "end", "move");
    private static final List<String> COMMANDS = Arrays.asList("start", "end", "move");

    public static String receiveInitialResponse() {
        return SCANNER.nextLine();
    }
}
