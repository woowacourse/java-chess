package chess.domain;

import java.util.Objects;

public class Price {

    private static final double PAWN_SPECIAL_PRICE = 0.5;

    private final double value;

    private Price(final double value) {
        this.value = value;
    }

    public static Price from(final double value) {
        return new Price(value);
    }

    public Price add(final Price other) {
        return Price.from(this.value + other.value);
    }

    public Price subtractPawnSpecialPrice(final Long pawnCount) {
        return Price.from(this.value - pawnCount * PAWN_SPECIAL_PRICE);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Price price = (Price) o;
        return Double.compare(price.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
