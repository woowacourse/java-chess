package chess.view.resposne;

import chess.domain.piece.Color;
import java.util.Arrays;

public enum ColorMapper {
    BLACK(Color.BLACK, "BLACK"),
    WHITE(Color.WHITE, "WHITE"),
    NONE(Color.NONE, "NONE");

    private final Color color;
    private final String colorName;

    ColorMapper(Color color, String colorName) {
        this.color = color;
        this.colorName = colorName;
    }

    public static String getColorName(Color color) {
        return Arrays.stream(values())
                .filter(it -> it.color == color)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"))
                .colorName;
    }
}
