package chess.model.position;

import java.util.List;
import java.util.Objects;

public class Distance {
    private static final Distance DISTANCE_ONE = new Distance(1);
    private static final Distance DISTANCE_TWO = new Distance(2);
    private final int value;

    public Distance(int value) {
        this.value = value;
    }

    public static Distance of(Position source, Position target, Direction direction) {
        if (Direction.diagonal().contains(direction)) {
            return new Distance(source.getFileGap(target));
        }
        if (Direction.vertical().contains(direction)) {
            return new Distance(source.getRankGap(target));
        }
        if (Direction.horizontal().contains(direction)) {
            return new Distance(source.getFileGap(target));
        }
        if (Direction.knight().contains(direction)) {
            return new Distance(Math.min(source.getFileGap(target), source.getRankGap(target)));
        }
        throw new IllegalArgumentException("거리를 구할 수 없습니다.");
    }

    public static List<Distance> oneStep() {
        return List.of(DISTANCE_ONE);
    }

    public static List<Distance> oneAndTwoStep() {
        return List.of(DISTANCE_ONE, DISTANCE_TWO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return value == distance.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
