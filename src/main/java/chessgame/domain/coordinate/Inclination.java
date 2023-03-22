package chessgame.domain.coordinate;

import java.util.Arrays;

public enum Inclination {

    POSITIVE_INFINITY(Double.POSITIVE_INFINITY),
    NEGATIVE_INFINITY(Double.NEGATIVE_INFINITY),
    TWO(2.0),
    MINUS_TWO(-2.0),
    ONE(1.0),
    MINUS_ONE(-1.0),
    HALF(0.5),
    MINUS_HALF(-0.5),
    ZERO(0.0),
    MINUS_ZERO(-0.0);


    private final double inclination;

    Inclination(double inclination) {
        this.inclination = inclination;
    }

    public static Inclination of(final double targetValue) {
        return Arrays.stream(values())
                     .filter(value -> Double.compare(value.inclination, targetValue) == 0)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 기울기입니다."));
    }
}
