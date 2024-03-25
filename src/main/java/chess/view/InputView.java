package chess.view;

import chess.controller.command.Command;
import java.util.Scanner;

public class InputView {
    public static final Scanner scanner = new Scanner(System.in);

    private final CommandParser commandParser;

    public InputView(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    public Command readCommand() {
        return commandParser.parse(scanner.nextLine());
    }
}
