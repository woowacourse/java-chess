package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {

    String getName();

    boolean isMovable(final Position from, final Position to);
}
