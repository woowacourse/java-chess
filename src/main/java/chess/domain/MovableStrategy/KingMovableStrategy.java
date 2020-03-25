package chess.domain.MovableStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;

public class KingMovableStrategy extends NonLeapableStrategy {
    public KingMovableStrategy() {
        this.movableDirections.addAll(Arrays.asList(MoveDirection.values()));
    }

    @Override
    boolean canMoveRange(Position source, Position target) {
        int fileInterval = Math.abs(source.calculateFileIntervalTo(target));
        int rankInterval = Math.abs(source.calculateRankIntervalTo(target));

        return fileInterval == 1 || rankInterval == 1;
    }
}
