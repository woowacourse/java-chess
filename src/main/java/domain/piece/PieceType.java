package domain.piece;

import domain.position.Position;

public enum PieceType {

    BISHOP(MoveTactic.DIAGONAL, AttackTactic.NOT_ATTACK),
    KING(MoveTactic.NEIGHBOR, AttackTactic.NOT_ATTACK),
    KNIGHT(MoveTactic.ONE_STRAIGHT_ONE_DIAGONAL, AttackTactic.NOT_ATTACK),
    PAWN(MoveTactic.FORWARD_STRAIGHT, AttackTactic.DIAGONAL),
    QUEEN(MoveTactic.STRAIGHT_DIAGONAL, AttackTactic.NOT_ATTACK),
    ROOK(MoveTactic.STRAIGHT, AttackTactic.NOT_ATTACK),
    NONE(MoveTactic.STOP, AttackTactic.NOT_ATTACK),
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
