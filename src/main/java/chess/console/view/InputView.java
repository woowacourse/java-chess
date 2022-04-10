package chess.console.view;

import chess.domain.command.Commands;
import java.util.Arrays;
import java.util.Scanner;

public final class InputView {

    private static final String COMMANDS_DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public static Commands requestCommands() {
        String commandInput = scanner.nextLine().trim();
        return new Commands(Arrays.asList(commandInput.split(COMMANDS_DELIMITER)));
    }
}
