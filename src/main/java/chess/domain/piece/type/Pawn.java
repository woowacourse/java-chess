package chess.domain.piece.type;

import chess.domain.position.Movement;
import chess.util.RouteCalculator;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Set;

public class Pawn extends Piece {

    public static final int DEFAULT_STEP = 1;
    private static final int INIT_AVAILABLE_STEP = 2;
    private static final Rank INIT_WHITE_RANK = Rank.TWO;
    private static final Rank INIT_BLACK_RANK = Rank.SEVEN;

    public Pawn(final Color color) {
        super(color);
    }

    public boolean canCatch(final Movement movement) {
        return movement.isDiagonal() && movement.getRankDistance() == Pawn.DEFAULT_STEP;
    }

    @Override
    public Set<Position> getRoute(final Movement movement) {
        if (canMove(movement)) {
            return RouteCalculator.getVerticalPositions(movement);
        }

        throw new IllegalArgumentException("[ERROR] 전략상 이동할 수 없는 위치입니다.");
    }

    private boolean canMove(final Movement movement) {
        if (this.isBlack()) {
            return canBlackMove(movement);
        }

        return canWhiteMove(movement);
    }

    private boolean canWhiteMove(final Movement movement) {
        if (isInitPosition(movement)) {
            return movement.isUp() && movement.getRankDistance() == INIT_AVAILABLE_STEP
                    || movement.isUp() && movement.getRankDistance() == DEFAULT_STEP;
        }

        return movement.isUp() && movement.getRankDistance() == DEFAULT_STEP;
    }

    private boolean canBlackMove(final Movement movement) {
        if (isInitPosition(movement)) {
            return movement.isDown() && movement.getRankDistance() == INIT_AVAILABLE_STEP
                    || movement.isDown() && movement.getRankDistance() == DEFAULT_STEP;
        }

        return movement.isDown() && movement.getRankDistance() == DEFAULT_STEP;
    }

    private boolean isInitPosition(final Movement movement) {
        if (this.isBlack()) {
            movement.getCurrent().isRank(INIT_BLACK_RANK);
        }

        return movement.getCurrent().isRank(INIT_WHITE_RANK);
    }
}
