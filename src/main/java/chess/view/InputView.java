package chess.view;

import chess.domain.game.Command;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String SPLIT_DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public Command readCommand() {
        final List<String> commands = splitAsList(scanner.nextLine());
        return new Command(commands);
    }

    private static List<String> splitAsList(final String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }
}
