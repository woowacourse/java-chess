package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Directions {

    private static final int PAWN_INDEX = 0;

    private final List<Direction> moveDirections;
    private final List<Direction> killDirections;

    public Directions(List<Direction> moveDirections,
        List<Direction> killDirections) {
        this.moveDirections = moveDirections;
        this.killDirections = killDirections;
    }

    public List<Position> movablePositions(List<Position> existPiecePositions,
        Position currentPosition, boolean iterable) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction moveDirection : moveDirections) {
            movablePositions.addAll(
                movablePositionsByDirection(
                    existPiecePositions, currentPosition, iterable, moveDirection
                )
            );
        }
        return movablePositions;
    }

    private List<Position> movablePositionsByDirection(List<Position> existPiecePositions,
        Position currentPosition, boolean iterable, Direction moveDirection) {
        List<Position> movablePositions = new ArrayList<>();
        do {
            if (currentPosition.invalidGo(moveDirection) ||
                existPiecePositions.contains(currentPosition.go(moveDirection))) {
                return movablePositions;
            }

            currentPosition = currentPosition.go(moveDirection);
            movablePositions.add(currentPosition);
        } while (iterable);

        return movablePositions;
    }

    public List<Position> killablePositions(List<Position> enemyPositions,
        Position currentPosition, boolean iterable) {
        List<Position> killablePositions = new ArrayList<>();
        for (Direction killDirection : killDirections) {
            killablePositions.addAll(
                killablePositionsByDirection(
                    enemyPositions, currentPosition, iterable, killDirection
                )
            );
        }
        return killablePositions;
    }

    private List<Position> killablePositionsByDirection(List<Position> enemyPositions,
        Position currentPosition, boolean iterable, Direction killDirection) {
        List<Position> killablePositions = new ArrayList<>();

        do {
            if (currentPosition.invalidGo(killDirection)) {
                return killablePositions;
            }

            currentPosition = currentPosition.go(killDirection);

            if (enemyPositions.contains(currentPosition)) {
                killablePositions.add(currentPosition);
                return killablePositions;
            }
        } while (iterable);

        return killablePositions;
    }

    public Optional<Position> pawnAdditionalPosition(List<Position> existPiecePositions,
        Position currentPosition) {
        Direction direction = moveDirections.get(PAWN_INDEX);

        Position oneStep = currentPosition.go(direction);
        Position twoStep = oneStep.go(direction);

        if (existPiecePositions.contains(oneStep) || existPiecePositions.contains(twoStep)) {
            return Optional.empty();
        }
        return Optional.of(twoStep);
    }
}
