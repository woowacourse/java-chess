package chess.view;

import chess.dto.GameCommand;
import chess.dto.GameRequest;
import chess.dto.UserRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    public static final String INVALID_INPUT_MESSAGE = "적절하지 않은 명령어입니다. 명령어를 확인해 주세요.";
    private static final Pattern PATTERN = Pattern.compile("[A-Z]\\d");
    private static final String COMMAND_DELIMITER = " ";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public GameRequest readStartCommand() {
        List<String> inputs = inputGameCommand();
        validateInputLength(inputs);

        GameCommand command = GameCommand.from(inputs.get(0));
        if (command.isSingleCommand()) {
            validateSingleCommand(inputs);
            return GameRequest.createCommand(command);
        }
        validateGameParameters(inputs);
        return GameRequest.createCommand(inputs.get(1), inputs.get(2));
    }

    private List<String> inputGameCommand() {
        return Arrays.stream(scanner.nextLine().split(COMMAND_DELIMITER))
                .map(String::trim)
                .map(String::toUpperCase)
                .toList();
    }

    private void validateInputLength(final List<String> inputs) {
        if (inputs.isEmpty()) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    private void validateSingleCommand(final List<String> inputs) {
        if (inputs.size() > 1) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    private void validateGameParameters(final List<String> inputs) {
        if (inputs.size() != 3) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
        validateGameParameter(inputs.get(1));
        validateGameParameter(inputs.get(2));
    }

    private void validateGameParameter(final String parameter) {
        if (!PATTERN.matcher(parameter).matches()) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    public UserRequest readUserRequest() {
        List<String> inputs = inputGameCommand();
        validateUserCommand(inputs);
        return UserRequest.from(inputs);
    }

    private void validateUserCommand(List<String> inputs) {
        if (inputs.size() != 2) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }
}
