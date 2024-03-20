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

    private final int westToEast;

    File(int westToEast) {
        this.westToEast = westToEast;
    }

    public File toEast() {
        if (westToEast >= 8) {
            throw new IllegalStateException("동쪽으로 이동할 수 없습니다.");
        }
        return find(westToEast + 1);
    }

    public File toWest() {
        if (westToEast <= 1) {
            throw new IllegalStateException("서쪽으로 이동할 수 없습니다.");
        }
        return find(westToEast - 1);
    }

    private File find(int westToEast) {
        return Arrays.stream(File.values())
                .filter(column -> column.westToEast == westToEast)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 세로 위치가 없습니다."));
    }
}
