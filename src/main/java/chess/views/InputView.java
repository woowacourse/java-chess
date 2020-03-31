package chess.views;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static InputDto inputCommand() {
        String string = scanner.nextLine();
        return new InputDto(string);
    }
}
