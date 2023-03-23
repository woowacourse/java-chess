package chess.domain.position;

import java.util.HashMap;
import java.util.Map;

import chess.domain.exception.IllegalMoveException;
import chess.domain.move.Direction;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final Map<Integer, File> FILE_CACHE = new HashMap<>();

    static {
        for (File file : values()) {
            FILE_CACHE.put(file.index, file);
        }
    }

    private final int index;

    File(int index) {
        this.index = index;
    }

    private static File indexOf(int index) {
        File file = FILE_CACHE.get(index);
        if (file == null) {
            throw new IllegalMoveException("체스판을 벗어났습니다");
        }
        return file;
    }

    public File move(Direction direction) {
        if (direction == Direction.LEFT) {
            return left();
        }
        if (direction == Direction.RIGHT) {
            return right();
        }
        return this;
    }

    private File left() {
        return indexOf(this.index - 1);
    }

    private File right() {
        return indexOf(this.index + 1);
    }

    public int minus(File file) {
        return this.index - file.index;
    }

    public int getIndex() {
        return index;
    }
}
