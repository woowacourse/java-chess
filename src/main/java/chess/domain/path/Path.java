package chess.domain.path;

import chess.domain.board.Square;
import chess.domain.board.Vectors;

public interface Path {
    Vectors movableArea(Square source);
}
