package chess.domain.piece;

import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.position.Position;

public class Knight extends Piece {

    private static final Knight WHITE = new Knight(Color.WHITE);
    private static final Knight BLACK = new Knight(Color.BLACK);
    private static final int GAP_LOWER_BOUND = 1;
    private static final int GAP_UPPER_BOUND = 2;

    private Knight(final Color color) {
        super(color, KNIGHT);
    }

    public static Knight from(final Color color) {
        if (color == Color.WHITE) {
            return WHITE;
        }
        return BLACK;
    }

    @Override
    protected boolean isValidMove(final Position start, final Position end) {
        final int fileGap = Math.abs(start.calculateFileGap(end));
        final int rankGap = Math.abs(start.calculateRankGap(end));

        return isMovable(fileGap, rankGap) || isMovable(rankGap, fileGap);
    }

    private boolean isMovable(final int firstGap, final int secondGap) {
        return firstGap == GAP_LOWER_BOUND && secondGap == GAP_UPPER_BOUND;
    }

    @Override
    protected boolean isValidTarget(final Piece target) {
        return color() != target.color();
    }
}
