package chess.domain.position;

import chess.domain.move.Direction;
import java.util.Arrays;
import java.util.Optional;

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

    private static Optional<File> indexOf(int index) {
        return Arrays.stream(values())
                .filter(file -> file.index == index)
                .findFirst();
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
        return indexOf(this.index - 1)
                .orElseThrow(UnsupportedOperationException::new);
    }

    private File right() {
        return indexOf(this.index + 1)
                .orElseThrow(UnsupportedOperationException::new);
    }

    public int getIndex() {
        return index;
    }
}
