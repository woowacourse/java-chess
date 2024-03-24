package chess.domain.position;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Rank {
    private static final Map<String, Rank> CACHE = IntStream.rangeClosed(1, 8)
                                                            .mapToObj(String::valueOf)
                                                            .collect(toMap(Function.identity(), Rank::new));

    private final String value;

    private Rank(String value) {
        this.value = value;
    }

    public static Rank valueOf(String value) {
        validate(value);
        return CACHE.get(value);
    }

    private static void validate(String value) {
        try {
            Integer.parseInt(value);
            validateInRange(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    private static void validateInRange(String value) {
        if(!CACHE.containsKey(value)) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    public Rank update(int value) {
        int index = Integer.parseInt(this.value) + value;
        return CACHE.get(String.valueOf(index));
    }

    public int subtractRank(Rank rank) {
        return Integer.parseInt(this.value) - Integer.parseInt(rank.value);
    }

    public int findDirection(Rank rank) {
        return Integer.compare(Integer.parseInt(rank.value), Integer.parseInt(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rank rank = (Rank) o;
        return value == rank.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
