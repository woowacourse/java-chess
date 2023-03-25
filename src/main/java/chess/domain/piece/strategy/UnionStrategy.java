package chess.domain.piece.strategy;

import chess.domain.position.Move;
import java.util.List;

public class UnionStrategy implements MoveStrategy {

    private final List<MoveStrategy> moveStrategies;

    private UnionStrategy(List<MoveStrategy> moveStrategies) {
        this.moveStrategies = moveStrategies;
    }

    public static UnionStrategy of(MoveStrategy... moveStrategies) {
        return new UnionStrategy(List.of(moveStrategies));
    }

    @Override
    public boolean canMove(Move move) {
        boolean canMove = false;
        for (MoveStrategy moveStrategy : moveStrategies) {
            if (moveStrategy.canMove(move)) {
                canMove = true;
            }
        }
        return canMove;
    }
}
