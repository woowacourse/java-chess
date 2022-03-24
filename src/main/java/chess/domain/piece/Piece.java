package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {

    boolean isMovable(final Position from, final Position to);

    boolean isSameColor(final Color color);

    String getName();
}
