package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private int index;

    Rank(int index) {
        this.index = index;
    }

    public static int size() {
        return values().length;
    }

    public Rank plus(int number) {
        return Rank.of(this.index + number);
    }

    public Rank minus(int number) {
        return Rank.of(this.index - number);
    }

    private static Rank of(int number) {
        return Arrays.stream(Rank.values())
                .filter(file -> file.index == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank의 범위를 초과하였습니다."));
    }

    public Rank reverse() {
        return Rank.of(values().length - index);
    }

    public int getRowNumber() {
        return index;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
