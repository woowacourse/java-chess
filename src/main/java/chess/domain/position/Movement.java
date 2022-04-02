package chess.domain.position;

import java.util.List;
import java.util.Objects;

public final class Movement {
    private final int dFile;
    private final int dRank;

    public Movement(int dFile, int dRank) {
        this.dFile = dFile;
        this.dRank = dRank;
    }

    Square add(Column column, Row row) {
        return new Square(column.add(dFile), row.add(dRank));
    }

    public Movement flipAboutX() {
        return new Movement(dFile, -1 * dRank);
    }

    public boolean hasSame(List<UnitDirection> directions) {
        return directions.stream()
                .map(UnitDirection::getDirection)
                .anyMatch(this::equals);
    }

    public boolean hasMultiple(List<UnitDirection> directions) {
        return directions.stream()
                .map(UnitDirection::getDirection)
                .anyMatch(direction -> this.getUnitDirection().equals(direction));
    }

    public Movement getUnitDirection() {
        if (dFile == 0 || dRank == 0) {
            return getUnitDirectionWithZero();
        }

        return getUnitDirectionWithOutZero();
    }

    private Movement getUnitDirectionWithZero() {
        if (dFile == 0 && dRank == 0) {
            return new Movement(dFile, dRank);
        }

        if (dFile == 0) {
            return new Movement(dFile, dRank / Math.abs(dRank));
        }

        return new Movement(dFile / Math.abs(dFile), dRank);
    }

    private Movement getUnitDirectionWithOutZero() {
        int gcd = gcd(Math.abs(dFile), Math.abs(dRank));
        return new Movement(dFile / gcd, dRank / gcd);
    }

    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movement movement = (Movement)o;
        return dFile == movement.dFile && dRank == movement.dRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dFile, dRank);
    }
}
