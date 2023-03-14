package chess;

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

    static public File from(final String value) {
        return Arrays.stream(File.values()).filter(file -> file.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File은 1에서 8사이의 값 이어야 합니다."));
    }

}
