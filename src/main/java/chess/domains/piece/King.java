package chess.domains.piece;

import chess.domains.position.Position;

public class King extends Piece {
    public King(PieceColor pieceColor) {
        super(pieceColor, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Position current, Position target) {
        return Math.abs(current.xGapBetween(target)) <= ONE_BLOCK
                && Math.abs(current.yGapBetween(target)) <= ONE_BLOCK;
    }
}
