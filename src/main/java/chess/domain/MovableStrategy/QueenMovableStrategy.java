package chess.domain.MovableStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;

public class QueenMovableStrategy extends NonLeapableStrategy {
    public QueenMovableStrategy() {
        this.movableDirections.addAll(Arrays.asList(MoveDirection.values()));
    }

    @Override
    boolean canMoveRange(Position source, Position target) {
        return true;
    }
}
