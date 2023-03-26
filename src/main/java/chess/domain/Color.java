package chess.domain;

import chess.domain.position.File;
import java.util.Arrays;

public enum Color {
    WHITE("white"),
    BLACK("black"),
    NONE("none");

    private final String label;

    Color(String label) {
        this.label = label;
    }

    public static Color findByLabel(String label) {
        return Arrays.stream(values())
                .filter(value -> value.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }

    public String getLabel() {
        return label;
    }
}
