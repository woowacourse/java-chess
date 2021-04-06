package chess.domain.piece;

import chess.domain.PieceDirection;
import chess.domain.Position;
import chess.domain.PositionInformation;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AvailableDirections {

    private static final int PAWN_INDEX = 0;

    private final List<PieceDirection> movePieceDirections;
    private final List<PieceDirection> killPieceDirections;

    public AvailableDirections(List<PieceDirection> movePieceDirections,
        List<PieceDirection> killPieceDirections) {
        this.movePieceDirections = movePieceDirections;
        this.killPieceDirections = killPieceDirections;
    }

    public List<Position> allMovablePositions(List<PositionInformation> existPiecePositions,
        PositionInformation currentPosition, boolean iterable) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(movablePositions(existPiecePositions, currentPosition, iterable));
        positions.addAll(killablePositions(existPiecePositions, currentPosition, iterable));
        return positions;
    }

    private List<Position> movablePositions(List<PositionInformation> existPiecePositions,
        PositionInformation currentPosition, boolean iterable) {
        List<Position> movablePositions = new ArrayList<>();
        List<Position> positions = existPiecePositions.stream()
            .map(PositionInformation::position)
            .collect(Collectors.toList());
        for (PieceDirection movePieceDirection : movePieceDirections) {
            movablePositions.addAll(
                movablePositionsByDirection(
                    positions, currentPosition.position(), iterable, movePieceDirection
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

    private List<Position> killablePositions(List<PositionInformation> existPiecePositions,
        PositionInformation currentPosition, boolean iterable) {
        List<Position> killablePositions = new ArrayList<>();
        TeamColor teamColor = currentPosition.teamColor();

        List<Position> teamPositions = existPiecePositions.stream()
            .filter(position -> position.isSameTeam(teamColor))
            .map(PositionInformation::position)
            .collect(Collectors.toList());

        List<Position> enemyPositions = existPiecePositions.stream()
            .filter(position -> position.isEnemyTeam(teamColor))
            .map(PositionInformation::position)
            .collect(Collectors.toList());

        for (PieceDirection killPieceDirection : killPieceDirections) {
            killablePositions.addAll(
                killablePositionsByDirection(
                    enemyPositions, teamPositions, currentPosition.position(), iterable,
                    killPieceDirection
                )
            );
        }
        return killablePositions;
    }

    private List<Position> killablePositionsByDirection(List<Position> enemyPositions,
        List<Position> teamPositions, Position currentPosition,
        boolean iterable, PieceDirection killPieceDirection) {
        List<Position> killablePositions = new ArrayList<>();

        do {
            if (currentPosition.invalidGo(killPieceDirection)) {
                return killablePositions;
            }

            currentPosition = currentPosition.go(killPieceDirection);

            if (teamPositions.contains(currentPosition)) {
                return killablePositions;
            }

            if (enemyPositions.contains(currentPosition)) {
                killablePositions.add(currentPosition);
                return killablePositions;
            }
        } while (iterable);

        return killablePositions;
    }

    public Optional<Position> pawnAdditionalPosition(List<PositionInformation> piecePositions,
        Position currentPosition) {
        List<Position> existPiecePositions = piecePositions.stream()
            .map(PositionInformation::position)
            .collect(Collectors.toList());
        PieceDirection pieceDirection = movePieceDirections.get(PAWN_INDEX);

        Position oneStep = currentPosition.go(pieceDirection);
        Position twoStep = oneStep.go(pieceDirection);

        if (existPiecePositions.contains(oneStep) || existPiecePositions.contains(twoStep)) {
            return Optional.empty();
        }
        return Optional.of(twoStep);
    }
}
