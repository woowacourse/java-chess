package chess.domain.piece;

import static chess.domain.piece.PieceType.PAWN;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;

public class Pawn extends Piece {

    private static final Pawn WHITE = new Pawn(Color.WHITE);
    private static final Pawn BLACK = new Pawn(Color.BLACK);
    private static final Map<Color, Integer> GAP_LOWER_BOUND = Map.of(Color.WHITE, -1, Color.BLACK, 1);
    private static final Map<Color, Integer> GAP_UPPER_BOUND = Map.of(Color.WHITE, -2, Color.BLACK, 2);
    private static final Map<Color, Rank> INITIAL_RANK = Map.of(Color.WHITE, Rank.TWO, Color.BLACK, Rank.SEVEN);
    private static final int VALID_STRAIGHT_GAP = 0;
    private static final int VALID_DIAGONAL_GAP = 1;

    private Pawn(final Color color) {
        super(color, PAWN);
    }

    public static Pawn from(final Color color) {
        if (color == Color.WHITE) {
            return WHITE;
        }
        return BLACK;
    }

    @Override
    public boolean isMovable(final Position start, final Position end, final Piece target) {
        return super.isMovable(start, end, target) || canMoveDiagonal(start, end, target);
    }

    private boolean canMoveDiagonal(final Position start, final Position end, final Piece target) {
        final int rankGap = start.calculateRankGap(end);
        final int fileGap = Math.abs(start.calculateFileGap(end));
        final boolean isOpponent = color().isOpponent(target.color());
        return rankGap == GAP_LOWER_BOUND.get(color()) && fileGap == VALID_DIAGONAL_GAP && isOpponent;
    }

    @Override
    protected boolean isValidMove(final Position start, final Position end) {
        if (start.calculateFileGap(end) != VALID_STRAIGHT_GAP) {
            return false;
        }
        final int rankGap = start.calculateRankGap(end);
        return canMoveStraightOnePosition(color(), rankGap) || canMoveStraightTwoPosition(start, color(), rankGap);
    }

    private boolean canMoveStraightOnePosition(final Color color, final int rankGap) {
        return rankGap == GAP_LOWER_BOUND.get(color);
    }

    private boolean canMoveStraightTwoPosition(final Position start, final Color color, final int rankGap) {
        return rankGap == GAP_UPPER_BOUND.get(color) && start.isSameRank(INITIAL_RANK.get(color));
    }

    @Override
    protected boolean isValidTarget(final Piece target) {
        return target.color() == Color.EMPTY;
    }
}
