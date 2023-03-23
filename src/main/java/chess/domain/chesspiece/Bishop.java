package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Bishop extends Piece {

    private static final Bishop BLACK_BISHOP = new Bishop(Side.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Side.WHITE);

    private Bishop(final Side side) {
        super(side, PieceInfo.BISHOP);
    }

    public static Bishop of(final Side side) {
        if (side == Side.BLACK) {
            return BLACK_BISHOP;
        }
        return WHITE_BISHOP;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && from.isDiagonal(to);
    }
}
