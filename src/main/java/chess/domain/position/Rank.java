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

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    static public Rank from(final int index) {
        return Arrays.stream(Rank.values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank는 1에서 8사이의 값 이어야 합니다."));
    }

    public int calculateDistance(final Rank rank) {
        return this.index - rank.index;
    }

    public Rank plus(final int fileDirection) {
        return Rank.from(index + fileDirection);
    }
}
