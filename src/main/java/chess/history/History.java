package chess.history;

import chess.domain.game.ActionHandler;

public interface History {
    
    void add(Move move);
    
    void clear(int id);
    
    void apply(ActionHandler action);
}
