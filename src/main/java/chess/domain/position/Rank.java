package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum Rank {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static int max() {
        return Arrays.stream(values())
                .mapToInt(it -> it.value)
                .max()
                .orElseThrow();
    }

    public int gap(Rank other) {
        return Math.abs(difference(other));
    }

    public int difference(Rank other) {
        return value - other.value;
    }

    public List<Rank> findBetween(Rank target) {
        if (this.value > target.value) {
            return makeBetween(targetToSource(target));
        }
        List<Rank> files = makeBetween(sourceToTarget(target));
        Collections.reverse(files);
        return files;
    }

    private List<Rank> makeBetween(Predicate<Rank> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private Predicate<Rank> targetToSource(Rank target) {
        return rank -> rank.value < this.value && rank.value > target.value;
    }

    private Predicate<Rank> sourceToTarget(Rank target) {
        return rank -> rank.value > this.value && rank.value < target.value;
    }
}
