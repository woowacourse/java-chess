package chess.views;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";
    private static final String COMMAND = "command";
    private static final String FROM = "from";
    private static final String TO = "to";

    public static InputDto inputCommand() {
        String string = scanner.nextLine();
        return new InputDto(string);
    }
}
