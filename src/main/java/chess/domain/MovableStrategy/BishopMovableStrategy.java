package chess.domain.MovableStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;

public class BishopMovableStrategy extends NonLeapableStrategy {
    public BishopMovableStrategy() {
        this.movableDirections.addAll(Arrays.asList(
                MoveDirection.NE,
                MoveDirection.SE,
                MoveDirection.SW,
                MoveDirection.NW));
    }

    @Override
    protected boolean canMoveRange(Position source, Position target) {
        return true;
    }
}
