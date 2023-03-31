package chess.view;

import chess.domain.Color;

import java.util.Arrays;

enum ColorForm {
    BLACK(Color.BLACK, "Black"),
    WHITE(Color.WHITE, "White");

    private final Color color;
    private final String displayName;

    ColorForm(final Color color, final String displayName) {
        this.color = color;
        this.displayName = displayName;
    }

    public static String convertToDisplayName(Color color) {
        return Arrays.stream(ColorForm.values())
                .filter(colorForm -> colorForm.color == color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move 만 입력할 수 있습니다."))
                .displayName;
    }
}
