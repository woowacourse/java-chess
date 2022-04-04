package chess.domain.board;

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
    EIGHT(8);

    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public static List<Rank> reverseValues() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static Rank of(String value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.rank == toInteger(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 rank축은 없습니다."));
    }

    private static int toInteger(String rank) {
        try {
            return Integer.parseInt(rank);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR 가로 축은 숫자로 입력해야합니다.");
        }
    }

    public int computeDiff(Rank target) {
        return target.rank - rank;
    }

    public Rank add(int value) {
        return Rank.of(String.valueOf(rank + value));
    }

    @Override
    public String toString() {
        return String.valueOf(rank);
    }
}
