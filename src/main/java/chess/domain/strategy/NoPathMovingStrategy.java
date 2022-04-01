package chess.domain.strategy;

import chess.domain.ChessBoardPosition;
import java.util.Collections;
import java.util.List;

public class NoPathMovingStrategy implements MovingStrategy {
    private static final String CANT_MOVE_EXCEPTION = "[ERROR] 움직일 수 없는 위치입니다.";
    private final List<ChessBoardPosition> ableMovement;

    public NoPathMovingStrategy(List<ChessBoardPosition> ableMovement) {
        this.ableMovement = ableMovement;
    }

    @Override
    public List<ChessBoardPosition> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        validateMovement(sourcePosition, targetPosition);
        return Collections.emptyList();
    }

    @Override
    public boolean isKillMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessBoardPosition movement = targetPosition.minus(sourcePosition);
        return ableMovement.stream()
                .anyMatch(it -> it.equals(movement));
    }

    private void validateMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessBoardPosition movement = targetPosition.minus(sourcePosition);
        if (!ableMovement.contains(movement)) {
            throw new IllegalArgumentException(CANT_MOVE_EXCEPTION);
        }
    }
}
