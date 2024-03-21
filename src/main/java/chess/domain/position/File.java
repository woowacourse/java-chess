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

    private static final int MIN_WEST_TO_EAST = 1;
    private static final int MAX_WEST_TO_EAST = 8;
    private static final int TO_EAST = 1;
    private static final int TO_WEST = -1;

    private final int westToEast;

    File(int westToEast) {
        this.westToEast = westToEast;
    }

    public File toEast() {
        if (westToEast >= MAX_WEST_TO_EAST) {
            throw new IllegalStateException("동쪽으로 이동할 수 없습니다.");
        }

        return find(westToEast + TO_EAST);
    }

    public File toWest() {
        if (westToEast <= MIN_WEST_TO_EAST) {
            throw new IllegalStateException("서쪽으로 이동할 수 없습니다.");
        }

        return find(westToEast + TO_WEST);
    }

    private File find(int westToEast) {
        return Arrays.stream(File.values())
                .filter(column -> column.westToEast == westToEast)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 세로 위치가 없습니다."));
    }

    public int calculateDifference(File file) {
        return file.westToEast - this.westToEast;
    }
}
