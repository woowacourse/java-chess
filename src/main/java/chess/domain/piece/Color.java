package chess.domain.piece;

import java.util.Arrays;

public enum Color {
    WHITE("WHITE"),
    BLACK("BLACK"),
    BLANK("BLANK");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public static Color findByValue(final String value){
        return Arrays.stream(values())
                .filter(targetValue -> targetValue.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 색상입니다."));
    }

    public String getValue() {
        return value;
    }
}
