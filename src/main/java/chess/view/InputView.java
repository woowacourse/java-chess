package chess.view;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int OPTION_START_INDEX = 1;

    private InputView() {
    }

    public static Command readCommand() {
        final List<String> inputs = parseInput(getInput());
        final CommandType commandType = CommandType.from(inputs.get(COMMAND_INDEX));
        final List<String> options = parseOption(inputs);
        return new Command(commandType, options);
    }

    private static String getInput() {
        return SCANNER.nextLine();
    }

    private static List<String> parseInput(final String input) {
        return Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toList());
    }

    private static List<String> parseOption(final List<String> inputs) {
        return inputs.stream()
                .skip(OPTION_START_INDEX)
                .collect(Collectors.toUnmodifiableList());
    }
}

