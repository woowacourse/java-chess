package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final int COORDINATE_X_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final CoordinateX coordinateX;
    private final Rank rank;

    private static final List<Position> TOTAL = new ArrayList<>();

    private Position(final CoordinateX coordinateX, final Rank rank) {
        this.coordinateX = coordinateX;
        this.rank = rank;
    }

    public static Position of(final CoordinateX coordinateX, final Rank rank) {
        Position newPosition = new Position(coordinateX, rank);
        return TOTAL.stream()
                .filter(position -> position.equals(newPosition))
                .findFirst()
                .orElse(newPosition);
    }

    public static Position from(final String position) {
        CoordinateX coordinateX = CoordinateX.from(position.substring(COORDINATE_X_INDEX, RANK_INDEX));
        Rank rank = Rank.from(Integer.parseInt(position.substring(RANK_INDEX)));

        return Position.of(coordinateX, rank);
    }

    public CoordinateX getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateXOrder() {
        return coordinateX.getOrder();
    }

    public int getRank() {
        return rank.getRank();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position)) return false;

        Position position = (Position) other;

        return coordinateX == position.coordinateX && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, rank);
    }
}
