package chess.domain.piece;

import static chess.domain.piece.PieceType.KING;

import chess.domain.position.Position;

public class King extends Piece {

    private static final King WHITE = new King(Color.WHITE);
    private static final King BLACK = new King(Color.BLACK);
    private static final int GAP_LOWER_BOUND = 0;
    private static final int GAP_UPPER_BOUND = 1;

    private King(final Color color) {
        super(color, KING);
    }

    public static King from(final Color color) {
        if (color == Color.WHITE) {
            return WHITE;
        }
        return BLACK;
    }

    @Override
    protected boolean isValidMove(final Position start, final Position end) {
        final int fileGap = Math.abs(start.calculateFileGap(end));
        final int rankGap = Math.abs(start.calculateRankGap(end));

        return canMoveStraight(fileGap, rankGap) || canMoveDiagonal(fileGap, rankGap);
    }

    private boolean canMoveStraight(final int fileGap, final int rankGap) {
        return (fileGap == GAP_LOWER_BOUND && rankGap == GAP_UPPER_BOUND) ||
                (fileGap == GAP_UPPER_BOUND && rankGap == GAP_LOWER_BOUND);
    }

    private boolean canMoveDiagonal(final int fileGap, final int rankGap) {
        return fileGap == GAP_UPPER_BOUND && rankGap == GAP_UPPER_BOUND;
    }

    @Override
    protected boolean isValidTarget(final Piece target) {
        return color() != target.color();
    }
}
