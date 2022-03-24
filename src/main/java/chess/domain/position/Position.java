package chess.domain.position;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

public final class Position {
    private final PositionX positionX;
    private final PositionY positionY;

    public Position(PositionX positionX, PositionY positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static Map<PositionY, List<Position>> groupByPositionY(List<Position> pawnPositions) {
        return pawnPositions.stream()
                .collect(groupingBy(position->position.positionY));
    }

    public int calculateDisplacementX(Position position) {
        return positionX.displacementFrom(position.positionX);
    }

    public int calculateDisplacementY(Position position) {
        return positionY.displacementFrom(position.positionY);
    }

    public int calculateDisplacementFrom(Position position) {
        return Math.max(Math.abs(calculateDisplacementY(position)), Math.abs(calculateDisplacementX(position)));
    }

    public int calculateXSlope(Position target, int routeLength) {
        return calculateDisplacementX(target) / routeLength;
    }

    public int calculateYSlope(Position target, int routeLength) {
        return calculateDisplacementY(target) / routeLength;
    }

    public Position displacement(int xDisplacement, int yDisplacement) {
        return new Position(positionX.displace(xDisplacement), positionY.displace(yDisplacement));
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
