package chess.domain;

import java.util.Arrays;

public enum Color {

    WHITE("White"),
    BLACK("Black"),
    ;

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public static Color from(final String name) {
        return Arrays.stream(values())
                .filter(it -> name.equals(it.name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 색상을 찾을 수 없습니다."));
    }

    public Color reverse() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }

    public String getName() {
        return name;
    }
}
