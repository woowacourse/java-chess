package chess.domain.position;

import java.util.Arrays;

public enum File {

    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String value;
    private final int index;

    File(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    static public File from(final String value) {
        return Arrays.stream(File.values()).filter(file -> file.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File은 1에서 8사이의 값 이어야 합니다."));
    }

    static public File from(final int index) {
        return Arrays.stream(File.values()).filter(file -> file.index == index)
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
