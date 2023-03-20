package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Queen extends Piece {

    private static final Queen blackQueen;
    private static final Queen whiteQueen;

    static {
        blackQueen = new Queen(Side.BLACK);
        whiteQueen = new Queen(Side.WHITE);
    }


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
        return isNotSameSide(piece) && (from.inLine(to) || from.inDiagonal(to));
    }
}
