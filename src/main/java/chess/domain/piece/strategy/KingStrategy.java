package chess.domain.piece.strategy;

import chess.domain.board.Column;
import chess.domain.board.Path;
import chess.domain.board.Position;
import chess.domain.piece.Side;

public class KingStrategy implements Strategy {
    @Override
    public boolean shouldBePlacedOn(final Position position, final Side side) {
        return isNotPawn(position, side)
            && position.isOn(Column.E);
    }

    @Override
    public boolean isMovableWithin(final Path path) {
        return path.distanceSquare() <= DIAGONAL_ONE_SQUARED
            && (path.isEndEmpty() || path.isEnemyOnEnd());
    }
}
