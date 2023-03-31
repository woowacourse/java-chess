package chess.domain.piece.strategy;

import chess.domain.position.Move;
import java.util.List;

public class IntersectionStrategy implements MoveStrategy {

    private final List<MoveStrategy> moveStrategies;

    private IntersectionStrategy(List<MoveStrategy> moveStrategies) {
        this.moveStrategies = moveStrategies;
    }

    public static IntersectionStrategy of(MoveStrategy... moveStrategies) {
        return new IntersectionStrategy(List.of(moveStrategies));
    }

    @Override
    public boolean canMove(Move move) {
        for (MoveStrategy moveStrategy : moveStrategies) {
            if (!moveStrategy.canMove(move)) {
                return false;
            }
        }
        return true;
    }
}
