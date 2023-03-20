package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class King extends Piece {

    private static final King blackKing;
    private static final King whiteKing;

    static {
        blackKing = new King(Side.BLACK);
        whiteKing = new King(Side.WHITE);
    }

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
        return this.isNotSameSide(piece) && from.inKingsRange(to);
    }
}
