package chess.model.position;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Difference {
    private final int amount;

    private Difference(int amount) {
        this.amount = amount;
    }

    public static Difference from(int amount) {
        validate(amount);
        return DifferenceCache.CACHE.get(amount);
    }

    private static void validate(int amount) {
        if (!DifferenceCache.CACHE.containsKey(amount)) {
            throw new IllegalArgumentException("File 혹은 Rank의 좌표차 절댓값은 " + DifferenceCache.MAX_AMOUNT + " 이하입니다.");
        }
    }

    public boolean isZero() {
        return amount == 0;
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public boolean isPositive() {
        return amount > 0;
    }

    public int plusByAbsoluteValue(Difference other) {
        return this.absoluteValue() + other.absoluteValue();
    }

    public int absoluteValue() {
        return abs(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Difference that = (Difference) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    private static class DifferenceCache {
        static final int MAX_AMOUNT = Rank.length() - 1;
        static final Map<Integer, Difference> CACHE = IntStream.range(MAX_AMOUNT * (-1), MAX_AMOUNT + 1)
                .boxed()
                .collect(toMap(identity(), Difference::new));

        private DifferenceCache() {
        }
    }
}
