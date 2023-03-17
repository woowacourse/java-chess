package chess.domain.position.move;

import chess.domain.piece.Piece;

public class PassingMove implements PieceMove {

    @Override
    public boolean isMovable(Piece piece, boolean isLastIndex) {
        return true;
    }
}
