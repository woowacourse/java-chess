package chess.domain.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private static final String NO_MATCHED_RANK_FOUND = "일치하는 Rank를 찾지 못했습니다 (1-8 입력 가능)";
    private static final String NO_RANK_FOUND_BY_POSITION = "해당 포지션의 Rank를 찾지 못했습니다";

    private final int rankNumber;

    Rank(int rankNumber) {
        this.rankNumber = rankNumber;
    }

    public static Rank from(int number) {
        return Arrays.stream(values())
                .filter(rank -> rank.rankNumber == number)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(NO_MATCHED_RANK_FOUND));
    }

    public static Rank from(String input) {
        return from(Integer.parseInt(input.trim()));
    }

    public static Rank fromOrdinal(String input) {
        return Arrays.stream(values())
                .filter(rank -> rank.toString().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public static Rank from(Position position) {
        return Arrays.stream(values())
                .filter(position::isSameRank)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(NO_RANK_FOUND_BY_POSITION));
    }

    public int dy(Rank another) {
        return another.rankNumber - this.rankNumber;
    }

    public List<Rank> between(Rank target) {
        Rank higherRank = getHigherRank(target);
        Rank lowerRank = getLowerRank(target);

        final List<Rank> rankBetweens = Arrays.stream(values())
                .filter(rank -> rank.rankNumber > lowerRank.rankNumber && rank.rankNumber < higherRank.rankNumber)
                .collect(Collectors.toList());

        return order(rankBetweens, target);
    }

    private Rank getHigherRank(Rank another) {
        if (this.rankNumber > another.rankNumber) {
            return this;
        }

        return another;
    }

    private Rank getLowerRank(Rank another) {
        if (this.rankNumber < another.rankNumber) {
            return this;
        }

        return another;
    }

    private List<Rank> order(List<Rank> ranks, Rank target) {
        if (this.rankNumber > target.rankNumber) {
            return ranks.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        }

        return ranks;
    }

    public int getRankNumber() {
        return rankNumber;
    }
}
