package chess.domain.game;

import chess.domain.position.Position;

public interface ActionHandler {
    
    Game start();
    
    Game move(Position from, Position to);
    
    Status status();
    
    Game end();
}
