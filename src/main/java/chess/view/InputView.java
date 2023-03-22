package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static chess.view.InputValidator.*;

public class InputView {

    private static final String MOVE_COMMAND_DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        String input = scanner.nextLine();
        if (GameCommandView.isValidCommandWithoutMove(input)) {
            return input;
        }
        List<String> commands = Arrays.asList(input.split(MOVE_COMMAND_DELIMITER));
        validateMoveCommand(commands);
        return commands.get(1) + commands.get(2);
    }

    private static void validateMoveCommand(List<String> commands) {
        validateBlank(commands);
        validateCommandIsMove(commands.get(0));
        validateCommandSize(commands);
        validateCommandLength(commands.get(1), commands.get(2));
        validateFile(commands.get(1).charAt(0));
        validateFile(commands.get(2).charAt(0));
        validateRank(commands.get(1).charAt(1));
        validateRank(commands.get(2).charAt(1));
    }
}
