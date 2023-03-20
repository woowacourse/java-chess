package chess.domain.position;

import java.util.Arrays;

public enum File {

    ONE(1),
    TWO(2),
    THREE( 3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int index;

    File(final int index) {
        this.index = index;
    }

    static public File from(final int index) {
        return Arrays.stream(File.values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File은 1에서 8사이의 값 이어야 합니다."));
    }

    public int calculateDistance(final File file) {
        return this.index - file.index;
    }

    public File plus(final int fileDirection) {
        return File.from(index + fileDirection);
    }
}
