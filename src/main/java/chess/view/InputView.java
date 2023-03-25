package chess.view;

import chess.controller.command.Command;
import chess.controller.command.CommandFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String DELIMITER = " ";
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public Command inputCommand() {
        String input = scanner.nextLine();
        List<String> commands = generateCommandsForm(input);
        return CommandFactory.from(commands);
    }

    private List<String> generateCommandsForm(final String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::strip)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
