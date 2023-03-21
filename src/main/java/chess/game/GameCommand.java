package chess.game;

import java.util.Arrays;
import java.util.List;

public class GameCommand {
    private static final String OUT_OF_BOUND_INDEX_EXCEPTION_MESSAGE = "[ERROR] 잘못된 파라미터 인덱스 입니다.";
    private static final String OVER_SIZE_INPUT_EXCEPTION_MESSAGE = "[ERROR] 입력의 최대 길이를 초과했습니다.";
    private static final String INVALID_COMMAND_EXCEPTION_MESSAGE = "[ERROR] 해당 커맨드가 존재하지 않습니다.";
    private static final String BLANK_INPUT_EXCEPTION_MESSAGE = "[ERROR] 공백은 입력될 수 없습니다.";
    private static final String NULL_INPUT_EXCEPTION_MESSAGE = "[ERROR] 입력은 null 값이 될 수 없습니다.";
    private static final List<String> ALLOW_COMMANDS = List.of("start", "end", "move", "status");
    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int MAX_COMMAND_LENGTH = 32;

    private final String command;
    private final List<String> parameters;

    private GameCommand(String command, List<String> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public static GameCommand of(String input) {
        validateNull(input);
        validateSize(input);
        List<String> tokens = Arrays.asList(input.split(DELIMITER));
        validateBlank(tokens);
        validateAllowCommand(tokens.get(COMMAND_INDEX));
        return new GameCommand(tokens.get(COMMAND_INDEX), tokens.subList(1, tokens.size()));
    }

    private static void validateNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException(NULL_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateSize(String input) {
        if (input.length() > MAX_COMMAND_LENGTH) {
            throw new IllegalArgumentException(OVER_SIZE_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateBlank(List<String> commands) {
        boolean hasBlank = commands.stream()
                .anyMatch(String::isBlank);
        if (commands.isEmpty() || hasBlank) {
            throw new IllegalArgumentException(BLANK_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateAllowCommand(String command) {
        if (!ALLOW_COMMANDS.contains(command)) {
            throw new IllegalArgumentException(INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }

    public String getCommand() {
        return command;
    }

    public String getParameter(int index) {
        if (index < 0 || index >= parameters.size()) {
            throw new IllegalArgumentException(OUT_OF_BOUND_INDEX_EXCEPTION_MESSAGE);
        }
        return parameters.get(index);
    }
}
