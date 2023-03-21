package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Knight extends Piece {
    private static final Knight blackKnight = new Knight(Side.BLACK);
    private static final Knight whiteKnight = new Knight(Side.WHITE);

    private Knight(final Side side) {
        super(side);
    }

    public static Knight of(final Side side) {
        if (side == Side.BLACK) {
            return blackKnight;
        }
        return whiteKnight;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && from.inLShape(to);
    }
}
