package chess.domain.position.move;

import chess.domain.piece.Piece;

public final class InvalidMove implements PieceMove {

    @Override
    public boolean isMovable(Piece piece, boolean isLastPiece) {
        return false;
    }
}
