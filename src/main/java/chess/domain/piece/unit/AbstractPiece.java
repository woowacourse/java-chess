package chess.domain.piece.unit;

import chess.domain.piece.property.PieceInfo;
import chess.domain.piece.property.Team;
import chess.domain.position.Position;
import chess.domain.position.XPosition;
import chess.domain.position.YPosition;
import chess.domain.piece.property.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public abstract class AbstractPiece implements Piece {

    private final PieceInfo pieceInfo;

    protected Map<Direction, List<Position>> directionalPositions;

    public AbstractPiece(final PieceInfo pieceInfo) {
        this.pieceInfo = pieceInfo;
    }

    @Override
    public boolean availableMove(final Position source, final Position target, final boolean targetIsNull) {
        return availableMove(source, target);
    }

    public boolean availableMove(final Position source, final Position target) {
        directionalPositions = calculateAvailableDirectionalPositions(source);
        return containsPosition(target);
    }

    protected Map<Direction, List<Position>> calculateAvailableDirectionalPositions(final Position source) {
        Map<Direction, List<Position>> directionalPositions = getDirections().stream()
                .collect(
                        Collectors.toMap(Function.identity(),
                                direction -> calculateAvailableDirectionByPosition(source, direction)
                        )
                );
        return directionalPositions;
    }

    protected abstract List<Position> calculateAvailableDirectionByPosition(final Position source,
                                                                            final Direction direction);

    @Override
    public List<Position> calculateRoute(final Position source, final Position target) {
        if (!isPawn()) {
            return calculateRoute(target);
        }
        List<Position> routePositions = new ArrayList<>();
        Direction direction = getDirection(target);
        final Position wayPoint = createWayPoint(source, direction);
        addRoutePosition(source, target, routePositions, wayPoint);
        return routePositions;
    }

    private List<Position> calculateRoute(final Position target) {
        List<Position> positions = getDirections().stream()
                .map(direction -> directionalPositions.get(direction))
                .filter(p -> p.contains(target))
                .findFirst()
                .orElse(new ArrayList<>());
        return calculateRouteBeforeTarget(target, positions);
    }

    private List<Position> calculateRouteBeforeTarget(Position target, List<Position> positions) {
        int targetIndex = positions.indexOf(target);
        if (targetIndex > 0) {
            return positions.subList(0, targetIndex);
        }
        return new ArrayList<>();
    }

    private Position createWayPoint(final Position source, final Direction direction) {
        final Position wayPoint = Position.of(
                XPosition.of(source.getXPosition() + direction.getXPosition() / 2),
                YPosition.of(source.getYPosition() + direction.getYPosition() / 2)
        );
        return wayPoint;
    }

    private void addRoutePosition(final Position source, final Position target, final List<Position> routePositions,
                                  final Position wayPoint) {
        if (!wayPoint.equals(source) && !wayPoint.equals(target)) {
            routePositions.add(wayPoint);
        }
    }

    public boolean checkSameTeam(final Team team) {
        return pieceInfo.checkSameTeam(team);
    }

    protected boolean checkOverRange(final int xPosition, final int yPosition) {
        return XPosition.checkRange(xPosition) && YPosition.checkRange(yPosition);
    }

    protected boolean containsPosition(final Position position) {
        return getDirections().stream()
                .map(direction -> directionalPositions.get(direction))
                .anyMatch(positions -> positions.contains(position));
    }

    @Override
    public void calculateDirections(final Position source) {
        this.directionalPositions = calculateAvailableDirectionalPositions(source);
    }

    @Override
    public boolean checkOneAndTwoSouthNorthDirections(final Position target) {
        return false;
    }

    @Override
    public Direction getDirection(final Position target) {
        return getDirections().stream()
                .filter(direction -> directionalPositions.get(direction).contains(target))
                .filter(direction -> directionalPositions.get(direction) != null)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 타켓 입력입니다."));

    }

    public String getSymbolByTeam() {
        return pieceInfo.getSymbolByTeam();
    }

    public String symbol() {
        return pieceInfo.symbol();
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public String toString() {
        return "AbstractPiece{" +
                "pieceInfo=" + pieceInfo +
                ", directionalPositions=" + directionalPositions +
                '}';
    }
}
