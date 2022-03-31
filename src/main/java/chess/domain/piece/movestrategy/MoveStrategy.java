package chess.domain.piece.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;

public interface MoveStrategy {

    boolean isMovable(Board board, Coordinate from, Coordinate to);
}
