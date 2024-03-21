package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Set;

public class Pawn extends Piece {

    public static final int DEFAULT_STEP = 1;
    private static final int INIT_AVAILABLE_STEP = 2;

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
            return this.position.isForwardWithDistance(target, INIT_AVAILABLE_STEP) || this.position.isForwardWithDistance(target,
                    DEFAULT_STEP);
        }
        return this.position.isForwardWithDistance(target, DEFAULT_STEP);
    }

    private boolean canBlackMoveTo(final Position target) {
        if (isInitPosition()) {
            return this.position.isForwardWithDistance(target, -INIT_AVAILABLE_STEP) || this.position.isForwardWithDistance(target, -DEFAULT_STEP);
        }
        return this.position.isForwardWithDistance(target, -DEFAULT_STEP);
    }

    private boolean isInitPosition() {
        if (color.equals(Color.WHITE)) {
            return this.position.isTwoRank();
        }
        return this.position.isSevenRank();
    }

    @Override
    public Set<Position> getRoute(final Position target) {
        return this.position.getVerticalMiddlePositions(target);
    }
}
