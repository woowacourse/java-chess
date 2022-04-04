package chess.domain.piece;

import java.util.Arrays;
import java.util.Objects;

public enum Color {

    BLACK("black"),
    WHITE("white"),
    NONE("none"),
    ;

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public static Color from(final String name) {
        return Arrays.stream(Color.values())
                .filter(color -> Objects.equals(color.name, name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Color입니다."));
    }

    public Color getOpposite() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getName() {
        return name;
    }
}
