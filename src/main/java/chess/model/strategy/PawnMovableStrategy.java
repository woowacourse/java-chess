package chess.model.strategy;

import chess.model.Rank;
import chess.model.board.Square;
import chess.model.strategy.move.Direction;
import chess.model.strategy.move.MoveType;
import java.util.List;

public class PawnMovableStrategy implements MovableStrategy {
    private static final int FIRST_MOVE_LIMIT = 2;
    private static final int NOT_FIRST_MOVE_LIMIT = 1;
    private static final int ATTACK_LIMIT = 1;

    private final List<Direction> nonAttackDirection;
    private final List<Direction> attackDirection;
    private final Rank startRank;

    public PawnMovableStrategy(List<Direction> nonAttackDirection, List<Direction> attackDirection, Rank startRank) {
        this.nonAttackDirection = nonAttackDirection;
        this.attackDirection = attackDirection;
        this.startRank = startRank;
    }

    @Override
    public boolean movable(Square source, Square target, MoveType moveType) {
        if (moveType.isAttack()) {
            return new LimitedMovableStrategy(attackDirection, ATTACK_LIMIT).movable(source, target, moveType);
        }
        return nonAttackMovable(source, target);
    }

    private boolean nonAttackMovable(Square source, Square target) {
        if (source.isSameRank(startRank)) {
            return new LimitedMovableStrategy(nonAttackDirection, FIRST_MOVE_LIMIT).movable(source, target,
                    MoveType.MOVE);
        }
        return new LimitedMovableStrategy(nonAttackDirection, NOT_FIRST_MOVE_LIMIT).movable(source, target,
                MoveType.MOVE);
    }
}
