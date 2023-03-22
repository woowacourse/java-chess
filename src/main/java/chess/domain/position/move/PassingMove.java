package chess.domain.position.move;

import chess.domain.piece.Piece;

public final class PassingMove implements PieceMove {

    @Override
    public boolean isMovable(Piece piece, boolean isLastPiece) {
        return true;
    }
}
