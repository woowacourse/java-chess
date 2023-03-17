package chess.domain.position.move;

import chess.domain.piece.Piece;

public class BlockingMove implements PieceMove {

    @Override
    public boolean isMovable(Piece piece,  boolean isLastPiece) {
        if (isLastPiece) {
            return true;
        }
        return piece.isEmpty();
    }


}
