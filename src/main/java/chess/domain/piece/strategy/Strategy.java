package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Side;

public interface Strategy extends InitialPositionStrategy, MovingStrategy {
    int DIAGONAL_ONE_SQUARED = 2;
    int KNIGHT_PATH_LENGTH_SQUARED = 5;
    int FORWARD_TWO_SQUARED = 4;
    int FORWARD_ONE_SQUARED = 1;

    default boolean isNotPawn(final Position position, final Side side) {
        return side == Side.WHITE && position.isOn(Row.ONE)
            || side == Side.BLACK && position.isOn(Row.EIGHT);
    }

    static Strategy empty() {
        return new Strategy() {
            @Override
            public boolean shouldBePlacedOn(final Position position, final Side side) {
                return false;
            }

            @Override
            public boolean isMovableWithin(final Path path) {
                return false;
            }
        };
    }
}
