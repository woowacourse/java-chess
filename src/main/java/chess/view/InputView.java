package chess.view;

import chess.Command;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command getCommand() {
        String command = scanner.next();
        return Command.of(command);
    }

    public String getMoveCommand() {
        return scanner.next();
    }
}
