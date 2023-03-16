package chess.domain.position.move;

import chess.domain.piece.Piece;

public class PawnForwardMove implements PieceMove {

    @Override
    public boolean isMovable(Piece piece) {
        return piece.isEmpty();
    }
}
