package chess.domain.piece;

import chess.domain.position.Move;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, Piece targetPiece) {
        return move.isDiagonal();
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
