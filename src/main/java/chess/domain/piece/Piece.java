package chess.domain.piece;

import chess.domain.piece.strategy.PieceRange;
import chess.domain.position.Notation;

public interface Piece {

    PieceRange movableFrom(Notation notation);

    boolean isColor(PieceColor color);

    boolean isPawn();

    boolean isKing();

    boolean isEmpty();
}
