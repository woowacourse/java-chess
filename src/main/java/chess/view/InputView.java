package chess.view;

import chess.view.dto.category.CategoryCommandType;
import chess.view.dto.game.GameCommandType;
import chess.view.dto.game.GameRequest;
import chess.view.dto.ready.ReadyRequest;
import java.util.Scanner;

public class InputView {

    public static final String INVALID_INPUT_MESSAGE = "적절하지 않은 명령어입니다";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public ReadyRequest askReadyCommand() {
        String[] inputs = inputGameCommand();
        validateReadyCommandSize(inputs);
        return ReadyRequest.from(inputs);
    }

    private void validateReadyCommandSize(String[] inputs) {
        if (inputs.length != 2) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    public CategoryCommandType askCategory() {
        String input = scanner.nextLine().strip().toUpperCase();
        return CategoryCommandType.from(input);
    }

    public GameRequest askGameCommand() {
        String[] inputs = inputGameCommand();
        validateHasLength(inputs);
        GameCommandType commandType = GameCommandType.from(inputs[0]);
        if (commandType.isSingleCommandType()) {
            validateSingleCommand(inputs);
            return GameRequest.createSingleCommand(commandType);
        }
        validateGameParameters(inputs);
        return GameRequest.createMoveCommand(inputs[1], inputs[2]);
    }

    public GameRequest askGameCommand(String turn) {
        printTurn(turn);
        return askGameCommand();
    }

    private void printTurn(String turn) {
        System.out.print(turn + "> ");
    }

    private String[] inputGameCommand() {
        String input = scanner.nextLine().strip().toUpperCase();
        return input.split(" ");
    }

    private void validateGameParameters(String[] inputs) {
        if (inputs.length != 3) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
        validateGameParameter(inputs[1]);
        validateGameParameter(inputs[2]);
    }

    private void validateGameParameter(String parameter) {
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
