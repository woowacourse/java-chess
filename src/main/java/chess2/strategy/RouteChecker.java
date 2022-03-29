package chess2.strategy;

import chess2.domain2.board2.Position;

@FunctionalInterface
public interface RouteChecker {

    boolean checkMovable(Position from, Position to);
}
