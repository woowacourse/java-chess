package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Bishop extends Piece {

    private static final Bishop blackBishop = new Bishop(Side.BLACK);
    private static final Bishop whiteBishop = new Bishop(Side.WHITE);

    private Bishop(final Side side) {
        super(side);
    }

    public static Bishop of(final Side side) {
        if (side == Side.BLACK) {
            return blackBishop;
        }
        return whiteBishop;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return from.inDiagonal(to) && isNotSameSide(piece);
    }
}
