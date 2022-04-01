package chess.model;

import java.util.List;
import java.util.Objects;

public class Distance {
    private final int value;

    private Distance(int value) {
        this.value = value;
    }

    public static Distance of(Position source, Position target) {
        return new Distance(1);
    }

    public static List<Distance> oneStep() {
        return List.of(new Distance(1));
    }

    public static List<Distance> pawn() {
        return List.of(new Distance(1), new Distance(2));
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
