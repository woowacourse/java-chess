package chess.domain;

import java.util.Arrays;

public enum Color {

    BLACK("black"),
    WHITE("white"),
    NONE("none")
    ;

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public static Color from(String colorName) {
        return Arrays.stream(values())
                .filter(color -> color.name.equals(colorName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색깔입니다."));
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public String getName() {
        return name;
    }
}
