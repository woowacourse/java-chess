package chess.domain.game;

import chess.domain.position.Position;

public interface ActionHandler2 {
    
    Game2 start();
    
    Game2 move(Position from, Position to);
    
    Status status();
    
    Game2 end();
}
