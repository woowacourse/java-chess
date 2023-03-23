package chess.command;

import chess.domain.game.Action;
import chess.domain.game.Status;

public interface QueryCommand {
    
    Status query(Action action);
}
