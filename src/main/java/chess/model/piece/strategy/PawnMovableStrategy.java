package chess.model.piece.strategy;

import chess.model.Direction;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import java.util.List;

public class PawnMovableStrategy implements MovableStrategy {
    private static final int FIRST_MOVE_LIMIT = 2;
    private static final int MOVE_LIMIT = 1;
    private final List<Direction> nonAttackDirection;
    private final List<Direction> attackDirection;

    public PawnMovableStrategy(List<Direction> nonAttackDirection, List<Direction> attackDirection) {
        this.nonAttackDirection = nonAttackDirection;
        this.attackDirection = attackDirection;
    }

    @Override
    public boolean movable(Piece source, Piece target) {
        if (source.isEnemy(target)) {
            return new LimitedMovableStrategy(attackDirection, MOVE_LIMIT).movable(source, target);
        }
        return nonAttackMovable(source, target);
    }

    private boolean nonAttackMovable(Piece source, Piece target) {
        if (!source.isPawn()) {
            return false;
        }
        Pawn pawn = (Pawn) source;
        if (pawn.isFirstLocation()) {
            return new LimitedMovableStrategy(nonAttackDirection, FIRST_MOVE_LIMIT).movable(source, target);
        }
        return new LimitedMovableStrategy(nonAttackDirection, MOVE_LIMIT).movable(source, target);
    }
}
