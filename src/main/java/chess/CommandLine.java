package chess;

import java.util.List;

public class CommandLine {
    
    public static final String INVALID_COMMAND_ERROR_MESSAGE = "잘못된 명령어입니다.";
    public static final String INVALID_ARGUMENT_ERROR_MESSAGE = "는 인자를 입력할 수 없습니다.";
    public static final String INVALID_ARGUMENT_COUNT_ERROR_MESSAGE = "는 인자를 2개만 가질 수 있습니다.";
    public static final String NO_ARGUMENT_ERROR_MESSAGE = "인자를 반환할 수 없는 명령입니다.";
    public static final String START = "start";
    public static final String MOVE = "move";
    public static final String END = "end";
    public static final int ZERO_ARGUMENT_SIZE = 1;
    public static final int TWO_ARGUMENT_SIZE = 3;
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    private static final List<String> VALID_COMMANDS = List.of(START, MOVE, END);
    private final List<String> tokens;
    
    public CommandLine(final List<String> tokens) {
        validate(tokens);
        this.tokens = tokens;
    }
    
    private void validate(final List<String> tokens) {
        String command = tokens.get(0);
        if (!VALID_COMMANDS.contains(command)) {
            throw new IllegalArgumentException(INVALID_COMMAND_ERROR_MESSAGE);
        }
        if (!command.equals(MOVE) && tokens.size() != ZERO_ARGUMENT_SIZE) {
            throw new IllegalArgumentException(command + INVALID_ARGUMENT_ERROR_MESSAGE);
        }
        if (command.equals(MOVE) && tokens.size() != TWO_ARGUMENT_SIZE) {
            throw new IllegalArgumentException(command + INVALID_ARGUMENT_COUNT_ERROR_MESSAGE);
        }
    }
    
    public String getCommand() {
        return this.tokens.get(0);
    }
    
    public List<String> getArguments() {
        if (this.tokens.size() != TWO_ARGUMENT_SIZE) {
            throw new IllegalStateException(NO_ARGUMENT_ERROR_MESSAGE);
        }
        return this.tokens.subList(SOURCE_INDEX, TARGET_INDEX);
    }
}
