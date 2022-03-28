package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final int COORDINATE_X_INDEX = 0;
    private static final int COORDINATE_Y_INDEX = 1;

    private final CoordinateX coordinateX;
    private final CoordinateY coordinateY;

    private static final List<Position> TOTAL = new ArrayList<>();

    private Position(final CoordinateX coordinateX, final CoordinateY coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public static Position of(final CoordinateX coordinateX, final CoordinateY coordinateY) {
        Position newPosition = new Position(coordinateX, coordinateY);
        return TOTAL.stream()
                .filter(position -> position.equals(newPosition))
                .findFirst()
                .orElse(newPosition);
    }

    public static Position from(final String position) {
        CoordinateX coordinateX = CoordinateX.from(position.substring(COORDINATE_X_INDEX, COORDINATE_Y_INDEX));
        CoordinateY coordinateY = CoordinateY.from(Integer.parseInt(position.substring(COORDINATE_Y_INDEX)));

        return Position.of(coordinateX, coordinateY);
    }

    public CoordinateX getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateXOrder() {
        return coordinateX.getOrder();
    }

    public int getCoordinateY() {
        return coordinateY.getOrder();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position)) return false;

        Position position = (Position) other;

        return coordinateX == position.coordinateX && coordinateY == position.coordinateY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }
}
