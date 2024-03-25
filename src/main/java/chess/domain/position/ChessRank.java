package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ChessRank {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    ;

    private final int index;

    ChessRank(int index) {
        this.index = index;
    }

    public static int minIndex() {
        return ONE.index;
    }

    public static int maxIndex() {
        return EIGHT.index();
    }

    public static List<ChessRank> findBetween(final ChessRank start, final ChessRank end) {
        if (start.index() < end.index()) {
            return findRanksBetween(start, end);
        }
        return findBetweenRanksWhenEndLessThanStart(end, start);
    }

    private static List<ChessRank> findRanksBetween(final ChessRank start, final ChessRank end) {
        List<ChessRank> ranks = new ArrayList<>();
        for (int index = start.index() + 1; index < end.index(); index++) {
            ranks.add(findByIndex(index));
        }
        return ranks;
    }

    private static List<ChessRank> findBetweenRanksWhenEndLessThanStart(final ChessRank start, final ChessRank end) {
        List<ChessRank> ranks = findRanksBetween(start, end);
        Collections.reverse(ranks);
        return ranks;
    }

    private static ChessRank findByIndex(final int rankIndex) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == rankIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스 랭크 범위에 해당하지 않는 인덱스입니다."));
    }

    public int index() {
        return index;
    }
}
