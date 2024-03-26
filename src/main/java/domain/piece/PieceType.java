package domain.piece;

import domain.position.Position;

public enum PieceType {

    BISHOP(MoveTactic.DIAGONAL, AttackTactic.ATTACK),
    KING(MoveTactic.NEIGHBOR, AttackTactic.ATTACK),
    KNIGHT(MoveTactic.ONE_STRAIGHT_ONE_DIAGONAL, AttackTactic.ATTACK),
    PAWN(MoveTactic.FORWARD_STRAIGHT, AttackTactic.DIAGONAL),
    QUEEN(MoveTactic.STRAIGHT_DIAGONAL, AttackTactic.ATTACK),
    ROOK(MoveTactic.STRAIGHT, AttackTactic.ATTACK),
    NONE(MoveTactic.STOP, AttackTactic.ATTACK),
    ;

    private final MoveTactic moveTactic;
    private final AttackTactic attackTactic;

    PieceType(MoveTactic moveTactic, AttackTactic attackTactic) {
        this.moveTactic = moveTactic;
        this.attackTactic = attackTactic;
    }

    public boolean canMove(Position source, Position target) {
        return moveTactic.canMove(source, target);
    }

    public boolean canAttack(Position source, Position target) {
        return attackTactic.canAttack(source, target);
    }

    public boolean isPawn() {
        return this == PAWN;
    }
}
