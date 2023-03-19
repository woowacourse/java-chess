package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static chess.view.GameCommand.END;
import static chess.view.InputValidator.*;

public class InputView {

    private static final String COMMANDS_DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public void readStart() {
        String input = scanner.nextLine();
        validateStartCommand(input);
    }

    public String readCommand() {
        String input = scanner.nextLine();
        if (END.getCommand().equals(input)) {
            return input;
        }

        List<String> commands = Arrays.asList(input.split(COMMANDS_DELIMITER));
        validateCommands(commands);

        return commands.get(1) + commands.get(2);
    }

    private static void validateCommands(List<String> commands) {
        validateBlank(commands);
        validateMoveCommand(commands.get(0));
        validateCommandSize(commands);
        validateCommandLength(commands.get(1), commands.get(2));
        validateFile(commands.get(1).charAt(0));
        validateFile(commands.get(2).charAt(0));
        validateRank(commands.get(1).charAt(1));
        validateRank(commands.get(2).charAt(1));
    }
}
