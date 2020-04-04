package chess.domain.RuleStrategy.nonLeapableStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;

public class QueenRule extends NonLeapable {

    public QueenRule() {
        this.movableDirections.addAll(Arrays.asList(MoveDirection.values()));
    }

    @Override
    protected boolean canMoveRange(Position source, Position target) {
        return true;
    }

}
