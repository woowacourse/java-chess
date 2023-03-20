package chess.domain.piece;

import chess.domain.position.RelativePosition;

public interface Piece {

    boolean isMobile(RelativePosition relativePosition);

    boolean isEmpty();

    boolean isBlack();

    boolean isWhite();

    PieceType getType();
}
