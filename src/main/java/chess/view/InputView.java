package chess.view;

import chess.controller.dto.PlayRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = " ";
    private static final String DEFAULT_POSITION = "A1";
    private static final int COMMAND_INDEX = 0;
    private static final int MAXIMUM_COMMAND_SIZE = 3;
    private static final int MINIMUM_COMMAND_SIZE = 1;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public PlayRequest readPlayCommand() {
        final String input = readInput();

        validateBlank(input);

        final List<String> commands = Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toList());
        validateInputCommand(commands);

        return createPlayRequest(commands);
    }

    private void validateInputCommand(final List<String> commands) {
        if (commands.size() > MAXIMUM_COMMAND_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 명령어입니다.");
        }
    }

    private PlayRequest createPlayRequest(final List<String> commands) {
        if (commands.size() == MINIMUM_COMMAND_SIZE) {
            return new PlayRequest(commands.get(COMMAND_INDEX), DEFAULT_POSITION, DEFAULT_POSITION);
        }
        if (commands.size() == MAXIMUM_COMMAND_SIZE) {
            return new PlayRequest(commands.get(COMMAND_INDEX), commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        }
        throw new IllegalArgumentException("유효하지 않은 명령어입니다.");
    }

    private void validateBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
    }

    private String readInput() {
        return scanner.nextLine()
                .trim();
    }
}
