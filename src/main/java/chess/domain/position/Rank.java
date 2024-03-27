package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private int index;

    Rank(int index) {
        this.index = index;
    }

    public static Rank from(String value) {
        return findRank(convertValueToIndex(value));
    }

    private static Rank findRank(int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("1~8까지 가능합니다."));
    }

    private static int convertValueToIndex(String value) {
        try {
            int index = Integer.parseInt(value);
            validateInRange(index);
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    private static void validateInRange(int index) {
        if (index < 1 || index > values().length) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    public Rank update(int value) {
        int index = this.index + value;
        if (index >= values().length) {
            throw new IllegalArgumentException("보드판 밖으로 이동할 수 없습니다.");
        }
        return findRank(index);
    }

    public int subtractRank(Rank rank) {
        return ordinal() - rank.ordinal();
    }

    public int findDirection(Rank rank) {
        return Integer.compare(rank.ordinal(), ordinal());
    }
}
