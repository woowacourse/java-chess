package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public GameCommand readCommand() {
        return GameCommand.from(SCANNER.next());
    }

    public String readPosition() {
        return SCANNER.next();
    }
}
