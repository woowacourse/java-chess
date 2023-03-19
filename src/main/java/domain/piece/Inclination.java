package domain.piece;

import java.util.Arrays;

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

    public static Inclination of(final double numericInclination) {
        return Arrays.stream(values())
                .filter(it -> Double.compare(it.inclination, numericInclination) == 0)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 정의되지 않은 방향입니다."));
    }
}
