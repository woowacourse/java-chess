package chess.model.strategy.move;

import java.util.Arrays;

public enum MoveType {
    ATTACK(true), MOVE(false);

    private final boolean isTargetEnemy;

    MoveType(boolean isTargetEnemy) {
        this.isTargetEnemy = isTargetEnemy;
    }

    public static MoveType of(boolean isTargetEnemy) {
        return Arrays.stream(MoveType.values())
                .filter(moveType -> moveType.isTargetEnemy == isTargetEnemy)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이동 타입입니다."));
    }

    public boolean isAttack() {
        return this.equals(ATTACK);
    }
}
