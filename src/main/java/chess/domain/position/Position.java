package chess.domain.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

public final class Position {
    private final static Map<String, Position> cache=new HashMap<>();

    private final PositionX positionX;
    private final PositionY positionY;

    private Position(PositionX positionX, PositionY positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static Position of(String position) {
        PositionX posX = PositionX.of(position.substring(0,1));
        PositionY posY = PositionY.of(position.substring(1));

        return cache.computeIfAbsent(position, ignored -> new Position(posX, posY));
    }

    public static Position of(PositionX positionX, PositionY positionY) {
        return Position.of(toKey(positionX, positionY));
    }

    private static String toKey(PositionX positionX, PositionY positionY) {
        return positionX.getName() + positionY.getName();
    }

    public static Map<PositionX, List<Position>> groupByPositionX(List<Position> pawnPositions) {
        return pawnPositions.stream()
                .collect(groupingBy(position -> position.positionX));
    }

    public int calculateDisplacementXTo(Position position) {
        return positionX.displacementTo(position.positionX);
    }

    public int calculateDisplacementYTo(Position position) {
        return positionY.displacementTo(position.positionY);
    }

    public int calculateMaxLinearLengthTo(Position position) {
        return Math.max(Math.abs(calculateDisplacementYTo(position)), Math.abs(calculateDisplacementXTo(position)));
    }

    public int calculateXSlope(Position target, int routeLength) {
        return calculateDisplacementXTo(target) / routeLength;
    }

    public int calculateYSlope(Position target, int routeLength) {
        return calculateDisplacementYTo(target) / routeLength;
    }

    public Position displacedOf(int xDisplacement, int yDisplacement) {
        return new Position(positionX.displacedOf(xDisplacement), positionY.displacedOf(yDisplacement));
    }

    public boolean isEndRank() {
        return positionY.isFirstOrLastRank();
    }

    public String getPositionName() {
        return positionX.getName() + positionY.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return positionX == position.positionX && positionY == position.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }
}
