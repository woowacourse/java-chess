package chess.domains.piece;

import chess.domains.position.Position;

public class King extends Piece {
    public King(PieceColor pieceColor) {
        super(pieceColor, "k");
    }

    @Override
    public boolean canMove(Position current, Position target) {
        return Math.abs(current.xGapBetween(target)) <= 1
                && Math.abs(current.yGapBetween(target)) <= 1;
    }
}
