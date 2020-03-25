package chess.domains.piece;

import chess.domains.position.Position;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor) {
        super(pieceColor, "b");
    }

    @Override
    public boolean canMove(Position current, Position target) {
        return Math.abs(current.xGapBetween(target)) == Math.abs(current.yGapBetween(target));
    }
}
