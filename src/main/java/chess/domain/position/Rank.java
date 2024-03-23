package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    private static final String OUT_OF_RANGE_ERROR = "더 이상 전진할 수 없습니다.";

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public Rank moveVertical(int index) {
        List<Rank> ranks = Rank.sorted();
        int targetIndex = ranks.indexOf(this) + index;

        validateIndexBound(targetIndex, ranks);

        return ranks.get(targetIndex);
    }

    private void validateIndexBound(int targetIndex, List<Rank> ranks) {
        if (targetIndex < 0 || ranks.size() <= targetIndex) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }
    }

    public int calculateDiff(Rank rank) {
        List<Rank> ranks = sorted();

        return ranks.indexOf(rank) - ranks.indexOf(this);
    }

    public static List<Rank> reversed() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.comparing(Rank::value).reversed())
                .toList();
    }

    public static List<Rank> sorted() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.comparing(Rank::value))
                .toList();
    }

    public int value() {
        return value;
    }
}
