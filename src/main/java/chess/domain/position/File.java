package chess.domain.position;

import java.util.Arrays;

public enum File {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String value;

    File(final String value) {
        this.value = value;
    }

    static File of(final String value) {
        return Arrays.stream(File.values())
                .filter((it) -> it.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 범위입니다."));
    }

    public File add(final int gap) {
        return File.of(String.valueOf(Integer.parseInt(value) + gap));
    }

    public int calculateDistance(final File target) {
        return Integer.parseInt(value) - Integer.parseInt(target.value);
    }

    public String getValue() {
        return value;
    }
}
