package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMAND_INPUT_INSTRUCTOR = ">> ";

    public static String inputCommand() {
        System.out.print(COMMAND_INPUT_INSTRUCTOR);
        return SCANNER.nextLine();
    }
}

