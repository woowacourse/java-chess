package chess.domain.piece.move;

import chess.domain.board.Point;

public interface Direction {

    Point nextOf(Point point);
}
