package chess.domains.piece;

import chess.domains.position.Position;

public class Knight extends Piece {
    public Knight(PieceColor pieceColor) {
        super(pieceColor, PieceType.KNIGHT);
    }

    @Override
    public boolean isValidMove(Position current, Position target) {
        return (Math.abs(current.xGapBetween(target)) == ONE_BLOCK && Math.abs(current.yGapBetween(target)) == TWO_BLOCKS)
                || (Math.abs(current.xGapBetween(target)) == TWO_BLOCKS && Math.abs(current.yGapBetween(target)) == ONE_BLOCK);
    }
}
