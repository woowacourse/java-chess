package chess.domain.MovableStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;

public class RookMovableStrategy extends NonLeapableStrategy {
    public RookMovableStrategy() {
        this.movableDirections.addAll(Arrays.asList(
                MoveDirection.N,
                MoveDirection.E,
                MoveDirection.S,
                MoveDirection.W));
    }

    @Override
    boolean canMoveRange(Position source, Position target) {
        return true;
    }
}
