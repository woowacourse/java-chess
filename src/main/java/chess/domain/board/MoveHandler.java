package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public interface MoveHandler {
    
    void checkColor(Position from, Position to, Color turn);
    
    void checkRoute(Position from, Position to);
    
    void move(Position from, Position to);
}
