package chess.domain.piece;

import chess.domain.position.Move;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, Piece targetPiece) {
        return move.isStraight();
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
