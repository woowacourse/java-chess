package chess.domain.RuleStrategy.nonLeapableStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;

public class RookRule extends NonLeapable {
    public RookRule() {
        this.movableDirections.addAll(Arrays.asList(
                MoveDirection.N,
                MoveDirection.E,
                MoveDirection.S,
                MoveDirection.W));
    }

    @Override
    protected boolean canMoveRange(Position source, Position target) {
        return true;
    }
}
