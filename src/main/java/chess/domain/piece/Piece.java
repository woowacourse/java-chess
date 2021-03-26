package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {

    boolean isColor(PieceColor color);

    boolean isPawn();

    boolean isKing();

    boolean isEmpty();
}
