package chess.domain.position;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private final PositionX positionX;
    private final PositionY positionY;

    public Position(PositionX positionX, PositionY positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static Position of(String value) {
        PositionX positionX = PositionX.of(value.substring(0, 1));
        PositionY positionY = PositionY.of(value.substring(1));
        return new Position(positionX, positionY);
    }

    public static Map<PositionY, List<Position>> groupByPositionY(List<Position> pawnPositions) {
        return pawnPositions.stream()
            .collect(groupingBy(position -> position.positionY));
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position)o;
        return positionX == position.positionX && positionY == position.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }
}
