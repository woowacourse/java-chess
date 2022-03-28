package chess.model.piece.strategy;

import chess.model.Direction;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import java.util.List;

public class PawnMovableStrategy implements MovableStrategy {
    private final List<Direction> nonAttackDirection;
    private final List<Direction> attackDirection;

    public PawnMovableStrategy(List<Direction> nonAttackDirection, List<Direction> attackDirection) {
        this.nonAttackDirection = nonAttackDirection;
        this.attackDirection = attackDirection;
    }

    @Override
    public boolean movable(Piece source, Piece target) {
        if (source.isEnemy(target)) {
            return new LimitedMovableStrategy(attackDirection, 1).movable(source, target);
        }
        return nonAttackMovable(source, target);
    }

    private boolean nonAttackMovable(Piece source, Piece target) {
        if (!source.isPawn()) {
            return false;
        }
        Pawn pawn = (Pawn) source;
        if (pawn.isFirstLocation()) {
            return new LimitedMovableStrategy(nonAttackDirection, 2).movable(source, target);
        }
        return new LimitedMovableStrategy(nonAttackDirection, 1).movable(source, target);
    }
}
