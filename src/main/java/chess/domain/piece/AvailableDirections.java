package chess.domain.piece;

import chess.domain.PieceDirection;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class AvailableDirections {

    private static final int PAWN_INDEX = 0;

    private final List<PieceDirection> movePieceDirections;
    private final List<PieceDirection> killPieceDirections;

    public AvailableDirections(List<PieceDirection> movePieceDirections,
        List<PieceDirection> killPieceDirections) {
        this.movePieceDirections = movePieceDirections;
        this.killPieceDirections = killPieceDirections;
    }

    public List<Position> movablePositions(List<Position> existPiecePositions,
        Position currentPosition, boolean iterable) {
        List<Position> movablePositions = new ArrayList<>();
        for (PieceDirection movePieceDirection : movePieceDirections) {
            movablePositions.addAll(
                movablePositionsByDirection(
                    existPiecePositions, currentPosition, iterable, movePieceDirection
                )
            );
        }
        return movablePositions;
    }

    private List<Position> movablePositionsByDirection(List<Position> existPiecePositions,
        Position currentPosition, boolean iterable, PieceDirection movePieceDirection) {
        List<Position> movablePositions = new ArrayList<>();
        do {
            if (currentPosition.invalidGo(movePieceDirection) ||
                existPiecePositions.contains(currentPosition.go(movePieceDirection))) {
                return movablePositions;
            }

            currentPosition = currentPosition.go(movePieceDirection);
            movablePositions.add(currentPosition);
        } while (iterable);

        return movablePositions;
    }

    public List<Position> killablePositions(List<Position> enemyPositions,
        Position currentPosition, boolean iterable) {
        List<Position> killablePositions = new ArrayList<>();
        for (PieceDirection killPieceDirection : killPieceDirections) {
            killablePositions.addAll(
                killablePositionsByDirection(
                    enemyPositions, currentPosition, iterable, killPieceDirection
                )
            );
        }
        return killablePositions;
    }

    private List<Position> killablePositionsByDirection(List<Position> enemyPositions,
        Position currentPosition, boolean iterable, PieceDirection killPieceDirection) {
        List<Position> killablePositions = new ArrayList<>();

        do {
            if (currentPosition.invalidGo(killPieceDirection)) {
                return killablePositions;
            }

            currentPosition = currentPosition.go(killPieceDirection);

            if (enemyPositions.contains(currentPosition)) {
                killablePositions.add(currentPosition);
                return killablePositions;
            }
        } while (iterable);

        return killablePositions;
    }

    public Optional<Position> pawnAdditionalPosition(List<Position> existPiecePositions,
        Position currentPosition) {
        PieceDirection pieceDirection = movePieceDirections.get(PAWN_INDEX);

        Position oneStep = currentPosition.go(pieceDirection);
        Position twoStep = oneStep.go(pieceDirection);

        if (existPiecePositions.contains(oneStep) || existPiecePositions.contains(twoStep)) {
            return Optional.empty();
        }
        return Optional.of(twoStep);
    }
}
