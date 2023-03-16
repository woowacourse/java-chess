package chess.view;

import chess.view.dto.Request;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String INVALID_INPUT_MESSAGE = "적절하지 않은 명령어입니다";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Request askCommand() {
        String input = scanner.nextLine().strip().toUpperCase();
        String[] inputs = input.split(" ");
        validateHasLength(inputs);
        Command command = Command.from(inputs[0]);
        if (List.of(Command.START, Command.END).contains(command)) {
            validateSingleCommand(inputs);
            return Request.createSingleCommand(command);
        }
        validateParameters(inputs);
        return Request.createMoveCommand(inputs[1], inputs[2]);
    }


    private void validateParameters(String[] inputs) {
        if (inputs.length != 3) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
        validateParameter(inputs[1]);
        validateParameter(inputs[2]);
    }

    private void validateParameter(String parameter) {
        if (!parameter.matches("[A-H][1-8]")) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    private void validateHasLength(String[] inputs) {
        if (inputs.length < 1) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    private void validateSingleCommand(String[] inputs) {
        if (inputs.length > 1) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }
}
