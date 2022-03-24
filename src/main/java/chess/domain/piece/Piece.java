package chess.domain.piece;

import java.util.Locale;

public abstract class Piece {

    private final String name;
    private final Color color;

    protected Piece(final Color color, final String name) {
        this.color = color;
        this.name = name;
    }

    public final String getName() {
        if (color.equals(Color.BLACK)) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }

    public boolean isSameColor(Color other) {
        return color == other;
    }
}
