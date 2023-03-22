package chess.action;

import chess.domain.Status;
import chess.domain.position.Position;

public interface Action {
    
    void start();
    
    void move(Position from, Position to);
    
    Status status();
    
    void end();
}
