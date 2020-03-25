package chess.domains.piece;

import chess.domains.position.Position;

public class Knight extends Piece {
    public Knight(PieceColor pieceColor) {
        super(pieceColor, "n");
    }

    @Override
    public boolean canMove(Position current, Position target) {
        return (Math.abs(current.xGapBetween(target)) == 1 && Math.abs(current.yGapBetween(target)) == 2)
                || (Math.abs(current.xGapBetween(target)) == 2 && Math.abs(current.yGapBetween(target)) == 1);
    }
}
