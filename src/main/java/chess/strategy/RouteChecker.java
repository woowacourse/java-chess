package chess.strategy;

import chess.domain.board.position.Position;

@FunctionalInterface
public interface RouteChecker {

    boolean checkMovable(Position from, Position to);
}
