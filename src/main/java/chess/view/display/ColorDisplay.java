package chess.view.display;

import chess.domain.color.Color;
import java.util.Arrays;

public enum ColorDisplay {
    WHITE(Color.WHITE, "흰색"),
    BLACK(Color.BLACK, "검은색");

    private final Color color;
    private final String display;

    ColorDisplay(Color color, String display) {
        this.color = color;
        this.display = display;
    }

    public static String findPieceDisplay(Color color) {
        return Arrays.stream(ColorDisplay.values())
                .filter(display -> display.color == color)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("올바르지 않은 Color입니다."))
                .display;
    }
}
