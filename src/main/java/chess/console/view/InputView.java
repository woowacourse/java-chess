package chess.console.view;

import chess.console.dto.CommandRequest;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMAND_REGEX = " ";
    private static final int SPLIT_LIMIT = -1;

    private InputView() {
    }

    public static CommandRequest inputCommand() {
        String input = SCANNER.nextLine();
        List<String> splitInputs = List.of(input.split(COMMAND_REGEX, SPLIT_LIMIT));
        return CommandRequest.of(splitInputs);
    }
}
