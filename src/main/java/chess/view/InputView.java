package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> inputCommand() {
        String input = scanner.nextLine();
        List<String> commands = generateCommandsForm(input);
        if (Command.from(commands.get(COMMAND_INDEX)) == Command.MOVE) {
            validateMoveCommand(commands);
        }
        return commands;
    }

    private List<String> generateCommandsForm(final String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::strip)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    private void validateMoveCommand(final List<String> commands) {
        if (commands.size() != 3) {
            throw new IllegalArgumentException("이동 명령어를 다시 확인하세요.");
        }
    }
}
