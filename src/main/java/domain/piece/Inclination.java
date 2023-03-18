package domain.piece;

public enum Inclination {

    POSITIVE_INFINITY(Double.POSITIVE_INFINITY),
    NEGATIVE_INFINITY(Double.NEGATIVE_INFINITY),
    ONE(1.0),
    MINUS_ONE(-1.0),
    ZERO(0.0),
    MINUS_ZERO(-0.0),
    ZERO_POINT_FIVE(0.5),
    MINUS_ZERO_POINT_FIVE(-0.5),
    TWO(2.0),
    MINUS_TWO(-2.0)
    ;

    private final double inclination;

    Inclination(final double inclination) {
        this.inclination = inclination;
    }

    public boolean isSameAs(final double other) {
        return Double.compare(this.inclination, other) == 0;
    }
}
