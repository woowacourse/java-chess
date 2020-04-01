package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Side;

public class PawnStrategy implements Strategy {
    final int DIAGONAL_ONE_SQUARED = 2;
    final int FORWARD_TWO_SQUARED = 4;
    final int FORWARD_ONE_SQUARED = 1;

    @Override
    public boolean shouldBePlacedOn(final Position position, final Side side) {
        return side == Side.WHITE && position.isOn(Row.TWO)
            || side == Side.BLACK && position.isOn(Row.SEVEN);
    }

    @Override
    public boolean isMovableWithin(final Path path) {
        if (path.isEnemyOnEnd()) {
            return path.distanceSquare() == DIAGONAL_ONE_SQUARED
                && path.isHeadingForward();
        }
        if (path.isOnInitialPosition()) {
            return (path.distanceSquare() == FORWARD_TWO_SQUARED || path.distanceSquare() == FORWARD_ONE_SQUARED)
                && path.isHeadingForward()
                && path.isEndEmpty();
        }
        return path.distanceSquare() == FORWARD_ONE_SQUARED
            && path.isHeadingForward() && path.isEndEmpty();
    }
}
