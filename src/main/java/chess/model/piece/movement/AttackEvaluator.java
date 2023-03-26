package chess.model.piece.movement;

import chess.model.piece.Camp;

public final class AttackEvaluator {

    private final Camp sourceCamp;
    private final Camp targetCamp;
    private final boolean empty;

    public AttackEvaluator(final Camp sourceCamp, final Camp targetCamp, final boolean empty) {
        this.sourceCamp = sourceCamp;
        this.targetCamp = targetCamp;
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isEnemy() {
        return !this.sourceCamp.isSameCamp(targetCamp);
    }

    public boolean isAttackAble() {
        return isEmpty() || isEnemy();
    }

    Camp sourceCamp() {
        return sourceCamp;
    }
}
