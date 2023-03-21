package chess.domain;

import chess.domain.position.Position;

@FunctionalInterface
public interface RouteCheck {
    
    void checkRoute(Position from, Position to);
    
}
