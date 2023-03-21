package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position source, Position target, Piece targetPiece) {
        if (!(source.isStraight(target) || source.isDiagonal(target))) {
            return false;
        }
        return isUnitMove(source.getDeltaFile(target), source.getDeltaRank(target));
    }

    private boolean isUnitMove(int deltaFile, int deltaRank) {
        return Math.max(Math.abs(deltaFile), Math.abs(deltaRank)) == 1;
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
