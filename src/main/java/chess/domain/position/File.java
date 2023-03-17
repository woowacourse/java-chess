package chess.domain.position;

import static chess.domain.position.Position.INVALID_POSITION;

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

    public File move(int deltaFile) {
        return indexOf(this.index + deltaFile);
    }

    private File indexOf(int index) {
        return Arrays.stream(values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_POSITION));
    }

    public static File from(String name) {
        try {
            return File.valueOf(name);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    public int getIndex() {
        return index;
    }
}
