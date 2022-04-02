package chess.domain.strategy;

import chess.domain.ChessBoardPosition;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PawnMovingStrategy implements MovingStrategy {
    private static final String CANT_MOVE_EXCEPTION = "[ERROR] 움직일 수 없는 위치입니다.";
    private final List<ChessBoardPosition> ableMovement;
    private final List<ChessBoardPosition> killMovement;
    private final List<ChessBoardPosition> initialPosition;

    public PawnMovingStrategy(List<ChessBoardPosition> blackTeamAbleMovement,
                              List<ChessBoardPosition> whiteTeamAbleMovement,
                              List<ChessBoardPosition> killMovement,
                              List<ChessBoardPosition> initialPosition) {
        this.ableMovement = new ArrayList<>();
        ableMovement.addAll(blackTeamAbleMovement);
        ableMovement.addAll(whiteTeamAbleMovement);
        this.killMovement = killMovement;
        this.initialPosition = initialPosition;
    }

    @Override
    public List<ChessBoardPosition> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        validateMovement(sourcePosition, targetPosition);
        List<ChessBoardPosition> path = new ArrayList<>();
        ChessBoardPosition unitMovement = getUnitMovement(sourcePosition, targetPosition);
        ChessBoardPosition currentPosition = sourcePosition.hardCopy().plus(unitMovement);
        while (!currentPosition.equals(targetPosition)) {
            path.add(currentPosition);
            currentPosition = currentPosition.plus(unitMovement);
        }
        return path;
    }

    private void validateMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        if (isInitialPosition(sourcePosition)) {
            validateInitialMovement(sourcePosition, targetPosition);
            return;
        }
        ChessBoardPosition movement = targetPosition.minus(sourcePosition);
        if (!ableMovement.contains(movement)) {
            throw new IllegalArgumentException(CANT_MOVE_EXCEPTION);
        }
    }

    private boolean isInitialPosition(ChessBoardPosition sourcePosition) {
        return initialPosition.contains(sourcePosition);
    }

    private void validateInitialMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessBoardPosition movement = targetPosition.minus(sourcePosition);
        List<ChessBoardPosition> ableInitialMovement = ableMovement.stream()
                .map(it -> it.plus(it))
                .collect(Collectors.toList());
        ableInitialMovement.addAll(ableMovement);
        if (!ableInitialMovement.contains(movement)) {
            throw new IllegalArgumentException(CANT_MOVE_EXCEPTION);
        }
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
        return killMovement.contains(direction);
    }
}
