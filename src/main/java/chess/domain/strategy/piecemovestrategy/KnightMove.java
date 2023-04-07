package chess.domain.strategy.piecemovestrategy;

import chess.domain.position.Position;

public final class KnightMove implements PieceMoveStrategy {

    private static final KnightMove INSTANCE = new KnightMove();
    private static final PieceType PIECE_TYPE = PieceType.KNIGHT;

    private KnightMove() {
    }

    public static KnightMove getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        final int verticalDistance = from.calculateVerticalDistance(to);

        return horizontalDistance + verticalDistance == 3
                && horizontalDistance != 0
                && verticalDistance != 0;
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
