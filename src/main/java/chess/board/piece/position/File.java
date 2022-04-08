package chess.board.piece.position;

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

    File(int index) {
        this.index = index;
    }

    public static File valueOf(int index) {
        return Arrays.stream(File.values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 값입니다 "));
    }

    public int absMinus(File file) {
        return Math.abs(index - file.index);
    }

    boolean isBiggerThan(File file) {
        return index > file.index;
    }

    public File getNext(int distance) {
        return File.valueOf(index + distance);
    }

    public int minus(File file) {
        return index - file.index;
    }
}
