package chess.domain.position;

import java.util.Arrays;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String value;
    private final int index;

    File(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    static public File from(final String value) {
        return Arrays.stream(File.values()).filter(rank -> rank.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank는 a에서 h사이의 값 이어야 합니다."));
    }

    static public File from(final int index) {
        return Arrays.stream(File.values()).filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank는 a에서 h사이의 값 이어야 합니다."));
    }

    int calculateDistance(final File file) {
        return this.index - file.index;
    }

    public File plus(final int rankDirection) {
        return File.from(index + rankDirection);
    }

    @Override
    public String toString() {
        return value;
    }
}

