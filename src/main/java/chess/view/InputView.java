package chess.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static List<String> buffer;

    private InputView() {
    }

    public static Command readCommand() {
        final List<String> inputs = parseInput(getInput());
        final Command command = Command.from(inputs.remove(COMMAND_INDEX));

        buffer = inputs;
        return command;
    }

    private static List<String> parseInput(final String input) {
        return Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toList());
    }

    public static List<String> getCoordinates() {
        return Collections.unmodifiableList(buffer);
    }

    private static String getInput() {
        return SCANNER.nextLine();
    }
}

