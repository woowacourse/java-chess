package domain.board.position;

import java.util.Arrays;

public enum Rank {

    EIGHT(7),
    SEVEN(6),
    SIX(5),
    FIVE(4),
    FOUR(3),
    THREE(2),
    TWO(1),
    ONE(0);

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank of(final int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("입력된 값: %d, 랭크가 잘못되었습니다.", index)));
    }

    public static Rank from(final String command) {
        return of(Integer.parseInt(command) - 1);
    }

    public int toIndex() {
        return index;
    }
}
