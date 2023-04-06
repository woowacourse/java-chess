package dao;

import domain.point.Point;

import java.util.Objects;

public class Movement {
    private final Point startingPoint;
    private final Point destinationPoint;

    public Movement(Point startingPoint, Point destinationPoint) {
        this.startingPoint = startingPoint;
        this.destinationPoint = destinationPoint;
    }

    public String getStartingSymbol() {
        return startingPoint.getSymbol();
    }

    public String getDestinationSymbol() {
        return destinationPoint.getSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return startingPoint.equals(movement.startingPoint) && destinationPoint.equals(movement.destinationPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingPoint, destinationPoint);
    }
}
