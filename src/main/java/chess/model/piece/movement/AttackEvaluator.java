package chess.model.piece.movement;

import chess.model.piece.Camp;
import chess.model.piece.PieceType;

public final class AttackEvaluator {

    private final Camp sourceCamp;
    private final Camp targetCamp;
    private final PieceType targetPieceType;

    public AttackEvaluator(final Camp sourceCamp, final Camp targetCamp, final PieceType targetPieceType) {
        this.sourceCamp = sourceCamp;
        this.targetCamp = targetCamp;
        this.targetPieceType = targetPieceType;
    }

    public boolean isEmpty() {
        return targetPieceType.isSamePieceType(PieceType.EMPTY);
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
