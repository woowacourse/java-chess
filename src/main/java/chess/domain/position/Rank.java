package chess.domain.position;

import static java.util.stream.Collectors.toMap;

public enum Rank {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT;

    public static Rank from(String value) {
        int index = getIndex(value);
        return values()[index];
    }

    private static int getIndex(String value) {
        try {
            int index = Integer.parseInt(value) - 1;
            validateInRange(index);
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    private static void validateInRange(int index) {
        if (index < 0 || index >= values().length) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    public Rank update(int value) {
        int index = ordinal() + value;
        if (index >= values().length) {
            throw new IllegalArgumentException("보드판 밖으로 이동할 수 없습니다.");
        }
        return values()[index];
    }

    public int subtractRank(Rank rank) {
        return ordinal() - rank.ordinal();
    }

    public int findDirection(Rank rank) {
        return Integer.compare(rank.ordinal(), ordinal());
    }
}
