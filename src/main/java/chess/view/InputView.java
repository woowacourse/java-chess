package chess.view;

import chess.controller.Command;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Command readCommand() {
        String input = SCANNER.next();
        return Command.of(input);
    }

    public String readLocation() {
        return SCANNER.next();
    }
}
