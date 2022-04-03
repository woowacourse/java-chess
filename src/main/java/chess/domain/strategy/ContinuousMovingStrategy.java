package chess.domain.strategy;

import chess.domain.ChessBoardPosition;
import java.util.ArrayList;
import java.util.List;

public class ContinuousMovingStrategy implements MovingStrategy {
    private static final String CANT_MOVE_EXCEPTION = "[ERROR] 움직일 수 없는 위치입니다.";
    private final List<ChessBoardPosition> ableMovement;

    public ContinuousMovingStrategy(List<ChessBoardPosition> ableMovement) {
        this.ableMovement = ableMovement;
    }

    @Override
    public List<ChessBoardPosition> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        List<ChessBoardPosition> path = new ArrayList<>();
        ChessBoardPosition unitMovement = getUnitMovement(sourcePosition, targetPosition);
        ChessBoardPosition currentPosition = sourcePosition.hardCopy().plus(unitMovement);
        while (!currentPosition.equals(targetPosition)) {
            path.add(currentPosition);
            currentPosition = currentPosition.plus(unitMovement);
        }
        return path;
    }

    private ChessBoardPosition getUnitMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessBoardPosition direction = targetPosition.minus(sourcePosition);
        return ableMovement.stream()
                .filter(it -> it.slope() == direction.slope())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CANT_MOVE_EXCEPTION));
    }

    @Override
    public boolean isKillMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessBoardPosition direction = targetPosition.minus(sourcePosition);
        return ableMovement.stream()
                .anyMatch(it -> it.slope() == direction.slope());
    }
}
