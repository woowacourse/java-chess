package chess.command;

import chess.action.Action;

public interface Command {
    
    String COMMAND_ERROR_PREFIX = "[COMMAND ERROR] ";
    
    void execute(Action action);
    
    boolean isNotEnd();
}
