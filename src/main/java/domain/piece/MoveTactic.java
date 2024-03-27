package domain.piece;

import domain.position.Position;

import java.util.function.BiPredicate;

public enum MoveTactic {

    DIAGONAL(Position::isDiagonal),
    STRAIGHT(Position::isNeighbor),
    FORWARD_STRAIGHT(Position::isForwardStraight),
    ONE_STRAIGHT_ONE_DIAGONAL(Position::isStraightDiagonal),
    STRAIGHT_DIAGONAL((source, target) -> source.isDiagonal(target) || source.isStraight(target)),
    NEIGHBOR(Position::isNeighbor),
    STOP((source, target) -> false);

    private final BiPredicate<Position, Position> tactic;

    MoveTactic(BiPredicate<Position, Position> tactic) {
        this.tactic = tactic;
    }

    public boolean canMove(Position source, Position target) {
        return tactic.test(source, target);
    }
}
