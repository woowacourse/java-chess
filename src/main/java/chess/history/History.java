package chess.history;

import chess.domain.game.ActionHandler;

public interface History {
    
    void add(Move move);
    
    void reset();
    
    void apply(ActionHandler action);
}
