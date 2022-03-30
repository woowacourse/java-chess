package chess.domain.piece.move;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

public interface MovingStrategy {
    boolean move(Route route, EmptyPoints emptyPoints);
}
