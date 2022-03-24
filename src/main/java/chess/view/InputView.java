package chess.view;

import chess.dto.CommandRequest;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static CommandRequest inputCommand() {
        String input = SCANNER.nextLine();
        List<String> splitInputs = List.of(input.split(" ", -1));
        return CommandRequest.of(splitInputs);
    }
}