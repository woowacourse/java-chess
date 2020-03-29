package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.piece.Side;

@FunctionalInterface
public interface InitialPositionStrategy {
    boolean shouldBePlacedOn(final Position position, final Side side);
}
