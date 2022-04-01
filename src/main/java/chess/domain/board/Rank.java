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

    private final int rankNumber;

    Rank(int rankNumber) {
        this.rankNumber = rankNumber;
    }

    public static Rank from(int number) {
        return Arrays.stream(values())
                .filter(rank -> rank.rankNumber == number)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public static Rank from(String input) {
        return from(Integer.parseInt(input.trim()));
    }

    public int dy(Rank another) {
        return another.rankNumber - this.rankNumber;
    }

    public List<Rank> between(Rank target) {
        final List<Rank> rankBetweens = Arrays.stream(values())
                .filter(rank -> rank.rankNumber > this.rankNumber && rank.rankNumber < target.rankNumber)
                .collect(Collectors.toList());

        return order(rankBetweens, target);
    }

    private List<Rank> order(List<Rank> ranks, Rank target) {
        if (this.rankNumber > target.rankNumber) {
            return ranks.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        }

        return ranks;
    }

    public static Rank from(Position position) {
        return Arrays.stream(values())
                .filter(position::isSameRank)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
