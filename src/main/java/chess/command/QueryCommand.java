package chess.command;

import chess.domain.game.ActionHandler;
import chess.domain.game.Status;

public interface QueryCommand {
    
    Status query(ActionHandler action);
}
