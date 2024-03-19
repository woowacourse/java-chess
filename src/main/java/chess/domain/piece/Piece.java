package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {
    boolean canMoveTo(Position target);
}
