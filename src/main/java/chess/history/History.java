package chess.history;

import chess.command.Command;
import chess.command.MoveCommand;
import java.util.List;

public interface History {
    
    void add(MoveCommand command);
    
    void reset();
    
    List<Command> getCommands();
}
