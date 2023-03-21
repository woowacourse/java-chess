package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {

    private static final int PRODUCT_OF_CHANGE = 2;

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position source, Position target, Piece targetPiece) {
        int deltaFile = source.getDeltaFile(target);
        int deltaRank = source.getDeltaRank(target);
        return Math.abs(deltaFile) * Math.abs(deltaRank) == PRODUCT_OF_CHANGE;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
