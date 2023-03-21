package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Queen extends Piece {

    private static final Queen blackQueen = new Queen(Side.BLACK);
    private static final Queen whiteQueen = new Queen(Side.WHITE);

    private Queen(final Side side) {
        super(side);
    }

    public static Queen of(final Side side) {
        if (side == Side.BLACK) {
            return blackQueen;
        }
        return whiteQueen;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && (from.isStraight(to) || from.isDiagonal(to));
    }
}
