package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class King extends Piece {

    private static final King blackKing = new King(Side.BLACK);
    private static final King whiteKing = new King(Side.WHITE);

    private King(final Side side) {
        super(side);
    }

    public static King of(final Side side) {
        if (side == Side.BLACK) {
            return blackKing;
        }
        return whiteKing;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && from.inKingsRange(to);
    }
}
