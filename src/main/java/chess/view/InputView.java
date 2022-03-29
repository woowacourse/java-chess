package chess.view;

import chess.domain.Command;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";

    public static Command requestFirstCommand() {
        return Command.firstCommand(scanner.nextLine());
    }

    public static List<String> requestCommandLine() {
        return List.of(scanner.nextLine().split(DELIMITER));
    }
}
