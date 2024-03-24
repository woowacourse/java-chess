package chess.domain.piece.type;

import chess.domain.Direction;
import chess.util.RouteCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import java.util.Set;

public class Pawn extends Piece {

    public static final int DEFAULT_STEP = 1;
    private static final int INIT_AVAILABLE_STEP = 2;
    private static final Rank INIT_WHITE_RANK = Rank.TWO;
    private static final Rank INIT_BLACK_RANK = Rank.SEVEN;

    public Pawn(final Color color) {
        super(color);
    }

    private boolean canWhiteMoveTo(final Position source, final Position target) {
        boolean isDownThanTarget = Direction.of(source, target).contains(Direction.DOWN);

        if (isInitPosition(source)) {
            return (isDownThanTarget && source.getRankDistance(target) == INIT_AVAILABLE_STEP)
                    || (isDownThanTarget && source.getRankDistance(target) == DEFAULT_STEP);
        }

        return isDownThanTarget && source.getRankDistance(target) == DEFAULT_STEP;
    }

    private boolean canBlackMoveTo(final Position source, final Position target) {
        boolean isDownThanTarget = Direction.of(source, target).contains(Direction.DOWN);

        if (isInitPosition(source)) {
            return (!isDownThanTarget && source.getRankDistance(target) == INIT_AVAILABLE_STEP)
                    || (!isDownThanTarget && source.getRankDistance(target) == DEFAULT_STEP);
        }
        return !isDownThanTarget && source.getRankDistance(target) == DEFAULT_STEP;
    }

    private boolean isInitPosition(final Position source) {
        if (color.equals(Color.WHITE)) {
            return source.isSameRank(INIT_WHITE_RANK);
        }
        return source.isSameRank(INIT_BLACK_RANK);
    }

    @Override
    public boolean canMoveTo(final Position source, final Position target) {
        if (color.equals(Color.WHITE)) {
            return canWhiteMoveTo(source, target);
        }
        return canBlackMoveTo(source, target);
    }

    @Override
    public Set<Position> getRoute(final Position source, final Position target) {
        return RouteCalculator.getVerticalMiddlePositions(source, target);
    }
}
