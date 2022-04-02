package chess.domain.position;

import java.util.Arrays;

public enum File {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    File(int value) {
        this.value = value;
    }

    public static File of(int value) {
        return Arrays.stream(File.values())
                .filter((it) -> it.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 범위입니다."));
    }

    public File add(int gap) {
        return File.of(value + gap);
    }

    public int calculateDistance(File target) {
        return value - target.value;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
