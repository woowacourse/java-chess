package chess.domain;

import java.util.List;

public class CommandLine {

    private static final String START = "start";
    private static final String MOVE = "move";
    private static final String END = "end";
    private static final int ZERO_ARGUMENT_SIZE = 1;
    private static final int TWO_ARGUMENT_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 3;
    private static final int MAIN_COMMAND_INDEX = 0;
    private static final List<String> VALID_COMMANDS = List.of(START, MOVE, END);
    private final List<String> tokens;
    
    public CommandLine(final List<String> tokens) {
        this.validate(tokens);
        this.tokens = tokens;
    }
    
    private void validate(final List<String> tokens) {
        String command = tokens.get(MAIN_COMMAND_INDEX);
        if (!VALID_COMMANDS.contains(command)) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
        if (!command.equals(MOVE) && tokens.size() != ZERO_ARGUMENT_SIZE) {
            throw new IllegalArgumentException(command + "는 인자를 입력할 수 없습니다.");
        }
        if (command.equals(MOVE) && tokens.size() != TWO_ARGUMENT_SIZE) {
            throw new IllegalArgumentException(command + "는 인자를 2개만 가질 수 있습니다.");
        }
    }
    
    public List<String> getArguments() {
        if (this.tokens.size() != TWO_ARGUMENT_SIZE) {
            throw new IllegalStateException("인자를 반환할 수 없는 명령입니다.");
        }
        return this.tokens.subList(SOURCE_INDEX, TARGET_INDEX);
    }
    
    public boolean isStart() {
        return START.equals(this.getCommand());
    }
    
    public String getCommand() {
        return this.tokens.get(MAIN_COMMAND_INDEX);
    }
    
    public boolean isMove() {
        return MOVE.equals(this.getCommand());
    }
    
    public boolean isEnd() {
        return END.endsWith(this.getCommand());
    }
}
