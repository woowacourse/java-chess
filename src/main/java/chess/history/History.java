package chess.history;

import chess.command.Command;
import java.util.List;

public interface History {
    
    String HISTORY_ERROR_PREFIX = "[HISTORY ERROR] ";
    
    void add(Command command);
    
    void reset();
    
    List<Command> getCommands();
}
