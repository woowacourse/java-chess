package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String value;
    private final int index;

    Rank(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    static public Rank from(final String value) {
        return Arrays.stream(Rank.values()).filter(file -> file.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File은 1에서 8사이의 값 이어야 합니다."));
    }

    static public Rank from(final int index) {
        return Arrays.stream(Rank.values()).filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File은 1에서 8사이의 값 이어야 합니다."));
    }

    int calculateDistance(final Rank rank) {
        return this.index - rank.index;
    }

    public Rank plus(final int fileDirection) {
        return Rank.from(index + fileDirection);
    }

    @Override
    public String toString() {
        return value;
    }
}
