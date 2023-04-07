package chess.domain.strategy.movestrategy;

import chess.domain.position.Position;

public final class DiagonalMove implements MoveStrategy {

    private static final DiagonalMove INSTANCE = new DiagonalMove();

    private DiagonalMove() {
    }

    public static DiagonalMove getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return from.calculateHorizontalDistance(to) == from.calculateVerticalDistance(to);
    }
}
