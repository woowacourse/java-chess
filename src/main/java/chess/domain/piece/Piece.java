package chess.domain.piece;

import chess.domain.board.Point;

public interface Piece {
    boolean isMovable(Point prev, Point next);
}
