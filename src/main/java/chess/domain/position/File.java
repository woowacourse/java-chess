package chess.domain.position;

import java.util.Arrays;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int index;

    File(final int index) {
        this.index = index;
    }

    static public File from(final int index) {
        return Arrays.stream(File.values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File은 a에서 h사이의 값 이어야 합니다."));
    }

    public int calculateDistance(final File file) {
        return this.index - file.index;
    }

    public File plus(final int rankDirection) {
        return File.from(index + rankDirection);
    }

    @Override
    public String toString() {
        return name();
    }
}

