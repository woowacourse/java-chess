package domain.piece;

import domain.position.Position;
import java.util.function.BiPredicate;

public enum Type {

    BISHOP(Position::isDiagonal),
    KING(Position::isNeighbor),
    KNIGHT(Position::isStraightDiagonal),
    PAWN((source, target) -> source.isForwardStraight(target, false)),
    QUEEN((source, target) -> source.isDiagonal(target) || source.isStraight(target)),
    ROOK(Position::isStraight),
    NONE((source, target) -> false),
    ;

    private final BiPredicate<Position, Position> tactic;

    Type(BiPredicate<Position, Position> tactic) {
        this.tactic = tactic;
    }

    public boolean canMove(Position source, Position target) {
        return tactic.test(source, target);
    }
}
