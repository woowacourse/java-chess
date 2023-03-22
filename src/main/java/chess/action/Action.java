package chess.action;

import chess.domain.position.Position;

public interface Action {
    
    void start();
    
    void move(Position from, Position to);
    
    void end();
}
