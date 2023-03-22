package chess.domain.distance;

import java.util.Objects;

public class Distances {
    private static final int THREE = 3;
    private static final int ZERO = 0;
    
    private final Distance columnDistance;
    private final Distance rowDistance;
    
    public Distances(int columnDistance, int rowDistance) {
        this(new Distance(columnDistance), new Distance(rowDistance));
    }
    
    private Distances(Distance columnDistance, Distance rowDistance) {
        this.columnDistance = columnDistance;
        this.rowDistance = rowDistance;
    }
    
    public Distances absoluteValue() {
        return new Distances(columnDistance.absoluteValue(), rowDistance.absoluteValue());
    }
    
    public Distances absoluteColumn() {
        return new Distances(columnDistance.absoluteValue(), rowDistance.distance());
    }
    
    public boolean isBothZero() {
        return columnDistance.isEqualTo(ZERO) && rowDistance.isEqualTo(ZERO);
    }
    
    public boolean isBothDifferent() {
        return !columnDistance.isEqualTo(rowDistance.distance());
    }
    
    public boolean isEitherGreaterThenOne() {
        return columnDistance.isGreaterThenOne() || rowDistance.isGreaterThenOne();
    }
    
    public boolean isContainsOneAndTwo() {
        return isNotContainsZero() && isSumThree();
    }
    
    private boolean isSumThree() {
        Distance sumDistance = columnDistance.add(rowDistance);
        return sumDistance.isEqualTo(THREE);
    }
    
    public boolean isNotContainsZero() {
        return !columnDistance.isEqualTo(ZERO) && !rowDistance.isEqualTo(ZERO);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distances distances = (Distances) o;
        return Objects.equals(columnDistance, distances.columnDistance) && Objects.equals(rowDistance, distances.rowDistance);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(columnDistance, rowDistance);
    }
    
    @Override
    public String toString() {
        return "Distances{" +
                "columnDistance=" + columnDistance +
                ", rowDistance=" + rowDistance +
                '}';
    }
}
