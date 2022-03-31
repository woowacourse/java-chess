package chess.view;

import chess.domain.command.Command;
import java.util.Arrays;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String COMMAND_DELIMITER = " ";

    public static Command inputCommand() {
        return new Command(Arrays.asList(SCANNER.nextLine().split(COMMAND_DELIMITER)));
    }
}
