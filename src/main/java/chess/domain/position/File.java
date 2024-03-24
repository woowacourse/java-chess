package chess.domain.position;

import java.util.Arrays;

public enum File {

    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 7;
    private static final int TO_EAST = 1;
    private static final int TO_WEST = -1;

    private final int index;

    File(int index) {
        this.index = index;
    }

    public File toEast() {
        if (index >= MAX_INDEX) {
            throw new IllegalStateException("동쪽으로 이동할 수 없습니다.");
        }

        return findFile(index + TO_EAST);
    }

    public File toWest() {
        if (index <= MIN_INDEX) {
            throw new IllegalStateException("서쪽으로 이동할 수 없습니다.");
        }

        return findFile(index + TO_WEST);
    }

    private File findFile(int index) {
        return Arrays.stream(File.values())
                .filter(column -> column.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 세로 위치가 없습니다."));
    }

    public int calculateDifference(File file) {
        return file.index - this.index;
    }
}
