package chess.controller.command;

import java.util.Arrays;
import java.util.List;

public enum CommandType {
    START("start", 1, "start 명령어는 값을 하나만 입력해야합니다."),
    MOVE("move", 3, "move 명령어는 '도착지'와 '출발지'에 대한 정보를 입력해야합니다."),
    END("end", 1, "end 명령어는 값을 하나만 입력해야합니다."),
    STATUS("status", 1, "status 명령어는 값을 하나만 입력해야합니다.");

    static final String INVALID_COMMAND_MESSAGE = "잘못된 명령어를 입력했습니다.";
    private static final String INVALID_COMMAND_SIZE_MESSAGE = "명령어는 최소 한글자 이상입니다.";
    private static final int COMMAND_INDEX = 0;
    private static final int MIN_INPUT_SIZE = 1;

    private final String gameCommand;
    private final int inputSize;
    private final String errorMessage;

    CommandType(String gameCommand, int inputSize, String errorMessage) {
        this.gameCommand = gameCommand;
        this.inputSize = inputSize;
        this.errorMessage = errorMessage;
    }

    public static CommandType from(List<String> input) {
        validateInputSize(input);
        CommandType commandType = Arrays.stream(values())
                .filter(command -> command.gameCommand.equalsIgnoreCase(input.get(COMMAND_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
        validateCommandSize(input, commandType);
        return commandType;
    }

    private static void validateCommandSize(List<String> input, CommandType commandType) {
        if (input.size() != commandType.inputSize) {
            throw new IllegalArgumentException(commandType.errorMessage);
        }
    }

    private static void validateInputSize(List<String> input) {
        if (input.size() < MIN_INPUT_SIZE) {
            throw new IllegalArgumentException(INVALID_COMMAND_SIZE_MESSAGE);
        }
    }
}
