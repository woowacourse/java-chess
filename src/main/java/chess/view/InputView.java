package chess.view;

import chess.view.dto.CommandType;
import chess.view.dto.ReadyRequest;
import chess.view.dto.Request;
import java.util.Scanner;

public class InputView {

    public static final String INVALID_INPUT_MESSAGE = "적절하지 않은 명령어입니다";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public ReadyRequest askReadyCommand() {
        String[] inputs = inputCommand();
        validateReadyCommandSize(inputs);
        return ReadyRequest.from(inputs);
    }

    private void validateReadyCommandSize(String[] inputs) {
        if (inputs.length != 2) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    public Request askCommand() {
        String[] inputs = inputCommand();
        validateHasLength(inputs);
        CommandType commandType = CommandType.from(inputs[0]);
        if (commandType.isSingleCommandType()) {
            validateSingleCommand(inputs);
            return Request.createSingleCommand(commandType);
        }
        validateParameters(inputs);
        return Request.createMoveCommand(inputs[1], inputs[2]);
    }

    public Request askCommand(String turn) {
        printTurn(turn);
        return askCommand();
    }

    private void printTurn(String turn) {
        System.out.print(turn + "> ");
    }

    private String[] inputCommand() {
        String input = scanner.nextLine().strip().toUpperCase();
        return input.split(" ");
    }

    private void validateParameters(String[] inputs) {
        if (inputs.length != 3) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
        validateParameter(inputs[1]);
        validateParameter(inputs[2]);
    }

    private void validateParameter(String parameter) {
        if (!parameter.matches("[A-Z]\\d")) {
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
