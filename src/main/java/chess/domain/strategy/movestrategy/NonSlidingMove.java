package chess.domain.strategy.movestrategy;

import chess.domain.position.Position;

public final class NonSlidingMove implements MoveStrategy {

    private static final NonSlidingMove INSTANCE = new NonSlidingMove();

    private NonSlidingMove() {
    }

    public static NonSlidingMove getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return from.calculateVerticalDistance(to) <= 1 && from.calculateHorizontalDistance(to) <= 1;
    }
}
