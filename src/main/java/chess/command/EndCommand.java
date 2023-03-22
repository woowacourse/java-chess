package chess.command;

import chess.action.Action;
import java.util.List;

public class EndCommand implements Command {
    
    public static final int END_ARGUMENTS_SIZE = 0;
    private static final String INVALID_ARGUMENT_ERROR_MESSAGE = "end 명령어는 인자를 입력할 수 없습니다.";
    
    public EndCommand(final List<String> arguments) {
        this.validate(arguments);
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != END_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(COMMAND_ERROR_PREFIX + INVALID_ARGUMENT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void execute(final Action action) {
        action.end();
    }
    
    @Override
    public boolean isNotEnd() {
        return false;
    }
}
