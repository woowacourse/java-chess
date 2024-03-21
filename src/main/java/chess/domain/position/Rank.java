package chess.domain.position;

import java.util.Arrays;
import java.util.List;

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

    public Rank moveVertical(int index) {
        List<Rank> ranks = List.of(Rank.values());
        int targetIndex = ranks.indexOf(this) + (index * -1);

        validateIndexBound(targetIndex, ranks);

        return ranks.get(targetIndex);
    }

    private void validateIndexBound(int targetIndex, List<Rank> ranks) {
        if (targetIndex < 0 || ranks.size() <= targetIndex) {
            throw new IndexOutOfBoundsException("더 이상 전진할 수 없습니다.");
        }
    }

    public int calculateDiff(Rank rank) {
        List<Rank> ranks = List.of(Rank.values());

        return ranks.indexOf(this) - ranks.indexOf(rank);
    }

    public static Rank findRankByValue(int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크입니다."));
    }
}
