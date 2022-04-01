package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    ;

    private final int rank;

    Rank(final int rank) {
        this.rank = rank;
    }

    public static Rank from(final int number) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.rank == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }

    public static Rank from(final String string) {
        return from(Integer.parseInt(string));
    }

    public static List<Rank> reversed() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(Rank::getRank).reversed())
                .collect(Collectors.toUnmodifiableList());
    }

    public int getRank() {
        return rank;
    }
}
