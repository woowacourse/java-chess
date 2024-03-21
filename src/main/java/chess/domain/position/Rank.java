package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private static final String RANK_NOT_FOUND_ERROR = "존재하지 않는 랭크입니다.";

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public Rank moveVertical(int index) {
        List<Rank> ranks = List.of(Rank.values());
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
        List<Rank> ranks = List.of(Rank.values());

        return ranks.indexOf(rank) - ranks.indexOf(this);
    }

    public static Rank findRankByValue(int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(RANK_NOT_FOUND_ERROR));
    }

    public static List<Rank> reverse() {
        List<Rank> reversedRanks = new ArrayList<>(List.of(Rank.values()));
        Collections.reverse(reversedRanks);

        return Collections.unmodifiableList(reversedRanks);
    }
}
