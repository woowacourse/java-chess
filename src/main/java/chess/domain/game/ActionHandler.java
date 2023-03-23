package chess.domain.game;

import chess.domain.position.Position;

public interface ActionHandler {
    
    void start();
    
    void move(Position from, Position to);
    
    Status status();
    
    void end();
}
