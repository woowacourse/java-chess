package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {

    boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget);

    boolean isSameColor(final Color color);

    boolean isJumpable();

    boolean isKing();

    String getName();
}
