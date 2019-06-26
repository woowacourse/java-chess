package chess.domain.path;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;

import java.util.Set;

public interface Path {
    Vectors movableArea(Square source);
}
