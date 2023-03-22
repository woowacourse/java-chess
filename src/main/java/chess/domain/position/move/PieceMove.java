package chess.domain.position.move;

import chess.domain.piece.Piece;

public interface PieceMove {

    boolean isMovable(Piece piece, boolean isLastPiece);
}
