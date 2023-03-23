package chess.command;

import chess.domain.game.ActionHandler;

public interface UpdateCommand {
    
    void update(ActionHandler action);
    
}
