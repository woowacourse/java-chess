package chess.domain.board;

import chess.domain.position.Position;

@FunctionalInterface
public interface RouteCheck {
    
    void checkRoute(Position from, Position to);
    
}
