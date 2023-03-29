package chess.history;

import chess.domain.game.ActionHandler2;

public interface History2 {
    
    void add(Move move);
    
    void clear(int id);
    
    void apply(ActionHandler2 action);
}
