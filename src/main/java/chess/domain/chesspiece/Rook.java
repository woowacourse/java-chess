package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Rook extends Piece {
    private static final Rook BLACK_ROOK = new Rook(Side.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Side.WHITE);

    private Rook(final Side side) {
        super(side, PieceInfo.ROOK);
    }

    public static Rook of(final Side side) {
        if (side == Side.BLACK) {
            return BLACK_ROOK;
        }
        return WHITE_ROOK;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && from.isStraight(to);
    }
}
