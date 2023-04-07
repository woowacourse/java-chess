package chess.domain.strategy.movestrategy;

import chess.domain.position.Position;

public final class StraightMove implements MoveStrategy {

    private static final StraightMove INSTANCE = new StraightMove();

    private StraightMove() {
    }

    public static StraightMove getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return from.calculateHorizontalDistance(to) == 0 || from.calculateVerticalDistance(to) == 0;
    }
}
