package chess.domain.piece.type;

import chess.RouteCalculator;
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

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        if (color.equals(Color.WHITE)) {
            return canWhiteMoveTo(target);
        }
        return canBlackMoveTo(target);
    }

    private boolean canWhiteMoveTo(final Position target) {
        if (isInitPosition()) {
            return (this.position.isDownWith(target) && this.position.getRankDistance(target) == INIT_AVAILABLE_STEP)
                    || (this.position.isDownWith(target) && this.position.getRankDistance(target) == DEFAULT_STEP);
        }
        return this.position.isDownWith(target) && this.position.getRankDistance(target) == DEFAULT_STEP;
    }

    private boolean canBlackMoveTo(final Position target) {
        if (isInitPosition()) {
            return (!this.position.isDownWith(target) && this.position.getRankDistance(target) == INIT_AVAILABLE_STEP)
                    || (!this.position.isDownWith(target) && this.position.getRankDistance(target) == DEFAULT_STEP);
        }
        return !this.position.isDownWith(target) && this.position.getRankDistance(target) == DEFAULT_STEP;
    }

    private boolean isInitPosition() {
        if (color.equals(Color.WHITE)) {
            return this.position.isRank(INIT_WHITE_RANK);
        }
        return this.position.isRank(INIT_BLACK_RANK);
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        return RouteCalculator.getVerticalMiddlePositions(this.position, target);
    }
}
