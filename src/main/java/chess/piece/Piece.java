package chess.piece;

import chess.position.Position;

public interface Piece {
    boolean canMoveTo(Position target);
}
