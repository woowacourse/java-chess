package chess.domain.strategy;

import chess.domain.ChessBoardPosition;
import java.util.ArrayList;
import java.util.List;

public class PawnMovingStrategy implements MovingStrategy {
    private static final String CANT_MOVE_EXCEPTION = "[ERROR] 움직일 수 없는 위치입니다.";
    private final List<ChessBoardPosition> ableMovement;
    private final List<ChessBoardPosition> killMovement;

    public PawnMovingStrategy(List<ChessBoardPosition> blackTeamAbleMovement,
                              List<ChessBoardPosition> whiteTeamAbleMovement,
                              List<ChessBoardPosition> killMovement) {
        this.ableMovement = new ArrayList<>();
        ableMovement.addAll(blackTeamAbleMovement);
        ableMovement.addAll(whiteTeamAbleMovement);
        this.killMovement = killMovement;
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

    @Override
    public boolean isKillMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessBoardPosition direction = targetPosition.minus(sourcePosition);
        return killMovement.stream()
                .anyMatch(it -> it.equals(direction));
    }

    private ChessBoardPosition getUnitMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessBoardPosition direction = targetPosition.minus(sourcePosition);
        return ableMovement.stream()
                .filter(it -> it.slope() == direction.slope())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CANT_MOVE_EXCEPTION));
    }
}
