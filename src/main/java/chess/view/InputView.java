package chess.view;

import java.util.Scanner;

import chess.view.dto.Request;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Request askCommand() {
        return IllegalArgumentExceptionHandler.handleExceptionByRepeating(() -> {
            String input = scanner.nextLine().strip().toUpperCase();
            String[] parameters = input.split(" ");
            validateHasLength(parameters);
            Command command = Command.from(parameters[0]);
            return createRequest(parameters, command);
        });
    }

    private Request createRequest(String[] parameters, Command command) {
        validateParameterCount(command, parameters);
        if (command == Command.START || command == Command.END || command == Command.STATUS) {
            return Request.createSingleCommand(command);
        }
        if (command == Command.MOVE) {
            validateMoveParameterFormats(parameters);
            return Request.createMoveCommand(parameters[1], parameters[2]);
        }
        throw new IllegalArgumentException("없는 명령어입니다");
    }

    private void validateMoveParameterFormats(String[] parameters) {
        validateFormatOf(parameters[1]);
        validateFormatOf(parameters[2]);
    }

    private void validateFormatOf(String parameter) {
        if (!parameter.matches("[A-Z]\\d")) {
            throw new IllegalArgumentException("매개변수 형식이 적절하지 않습니다");
        }
    }

    private void validateHasLength(String[] inputs) {
        if (inputs.length < 1) {
            throw new IllegalArgumentException("명령어가 없습니다");
        }
    }

    private void validateParameterCount(Command command, String[] parameters) {
        if (!command.hasParameterCount(parameters.length)) {
            throw new IllegalArgumentException("인자의 개수가 잘못되었습니다");
        }
    }
}
