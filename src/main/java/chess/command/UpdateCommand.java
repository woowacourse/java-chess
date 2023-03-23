package chess.command;

import chess.domain.game.Action;

public interface UpdateCommand {
    
    void update(Action action);
    
}
