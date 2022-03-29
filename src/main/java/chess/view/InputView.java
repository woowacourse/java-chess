package chess.view;

import chess.domain.command.InputCommand;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String COMMAND_DELIMITER = " ";

    public static InputCommand inputCommand() {
        List<String> commands = Arrays.asList(SCANNER.nextLine().split(COMMAND_DELIMITER));

        return InputCommand.from(commands.get(0));
    }
}
