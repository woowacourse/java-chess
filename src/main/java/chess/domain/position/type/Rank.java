package chess.domain.position.type;

import chess.domain.piece.type.Direction;
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

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank of(String rankInput) {
        return findRank(Integer.parseInt(rankInput));
    }

    private static Rank findRank(int value) {
        return Arrays.stream(values())
            .filter(rank -> rank.getValue() == value)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 rank값 입니다."));
    }

    public Rank getMovedRank(Direction direction) {
        int movedY = value + direction.getY();
        return findRank(movedY);
    }

    public static List<Rank> reversedRanks() {
        return Arrays.stream(values())
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
    }

    public boolean isDiff(Rank destinationRank, int diff) {
        return Math.abs(value - destinationRank.getValue()) == diff;
    }

    public int getValue() {
        return value;
    }
}
