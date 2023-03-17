package chess.domain.position.move;

import chess.domain.piece.Piece;

public class InvalidMove implements PieceMove {

    @Override
    public boolean isMovable(Piece piece, boolean isLastIndex) {
        return false;
    }
}
