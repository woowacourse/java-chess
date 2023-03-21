package chess.domain.piece;

import chess.domain.position.Move;

public class Knight extends Piece {

    private static final int PRODUCT_OF_CHANGE = 2;

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, Piece targetPiece) {
        return Math.abs(move.getDeltaFile() * move.getDeltaRank()) == PRODUCT_OF_CHANGE;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
