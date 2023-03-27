package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class King extends Piece {

    private static final King BLACK_KING = new King(Side.BLACK);
    private static final King WHITE_KING = new King(Side.WHITE);

    private King(final Side side) {
        super(side, PieceInfo.KING);
    }

    public static King from(final Side side) {
        if (side == Side.BLACK) {
            return BLACK_KING;
        }
        return WHITE_KING;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && isKingsRange(from, to);
    }

    private boolean isKingsRange(final Square from, final Square to) {
        final int rankDistance = from.rankDistanceTo(to);
        final int fileDistance = from.fileDistanceTo(to);
        return (rankDistance == 1 || rankDistance == 0) &&
                (fileDistance == 1 || fileDistance == 0);
    }
}
