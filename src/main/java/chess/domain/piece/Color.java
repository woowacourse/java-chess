package chess.domain.piece;

import java.util.Arrays;

public enum Color {

    BLACK("black"),
    WHITE("white"),
    NONE(""),
    ;

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public static Color from(final String name) {
        return Arrays.stream(Color.values())
                .filter(color -> color.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Color 입니다."));
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
