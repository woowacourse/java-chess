package chess.domain.distance;

import java.util.Objects;

public class Distance {
    private final int distance;
    
    public Distance(int distance) {
        this.distance = distance;
    }
    
    public Integer absoluteValue() {
        return Math.abs(distance);
    }
    
    public boolean isGreaterThenOne() {
        return distance > 1;
    }
    
    public Distance add(Distance otherDistance) {
        return new Distance(this.distance + otherDistance.distance);
    }
    
    public boolean isEqualTo(int otherDistance) {
        return distance == otherDistance;
    }
    
    public int distance() {
        return distance;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance1 = (Distance) o;
        return distance == distance1.distance;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(distance);
    }
    
    @Override
    public String toString() {
        return "Distance{" +
                "distance=" + distance +
                '}';
    }
}
