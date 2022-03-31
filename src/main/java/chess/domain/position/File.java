package chess.domain.position;

import java.util.Arrays;

public enum File {

    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String value;

    File(final String value) {
        this.value = value;
    }

    static File of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 범위입니다."));
    }

    File add(final int gap) {
        return File.of(String.valueOf((char) (value.charAt(0) + gap)));
    }

    int calculateDistance(final File target) {
        return value.charAt(0) - target.value.charAt(0);
    }

    String value() {
        return value;
    }
}
