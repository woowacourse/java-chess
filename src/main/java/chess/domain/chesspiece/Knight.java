package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Knight extends Piece {
    private static final Knight BLACK_KNIGHT = new Knight(Side.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Side.WHITE);

    private Knight(final Side side) {
        super(side, PieceInfo.KNIGHT);
    }

    public static Knight of(final Side side) {
        if (side == Side.BLACK) {
            return BLACK_KNIGHT;
        }
        return WHITE_KNIGHT;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && isLShape(from, to);
    }

    private boolean isLShape(final Square from, final Square to) {
        final int rankDistance = from.rankDistanceTo(to);
        final int fileDistance = from.fileDistanceTo(to);
        if (rankDistance == 0 || fileDistance == 0) {
            return false;
        }
        return rankDistance + fileDistance == 3;
    }
}
