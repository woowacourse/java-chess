package chess.domain.piece;

import java.util.Arrays;

public enum Color {
    WHITE("백"),
    BLACK("흑"),
    NOTHING("NOTHING");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public boolean isSameAs(Color color) {
        return this.equals(color);
    }

    public String getName() {
        return name;
    }
}
