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

    private String value;

    File(String value) {
        this.value = value;
    }

    public static File of(String value){
        return Arrays.stream(File.values())
                .filter((it) -> it.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 범위입니다."));
    }

    public String getValue() {
        return value;
    }
}
