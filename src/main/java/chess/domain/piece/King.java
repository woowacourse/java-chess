package chess.domain.piece;

import chess.domain.position.Move;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, Piece targetPiece) {
        if (!(move.isStraight() || move.isDiagonal())) {
            return false;
        }
        return isUnitMove(move.getDeltaFile(), move.getDeltaRank());
    }

    private boolean isUnitMove(int deltaFile, int deltaRank) {
        return Math.max(Math.abs(deltaFile), Math.abs(deltaRank)) == 1;
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
