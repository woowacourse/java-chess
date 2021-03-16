package chess.domain.piece;

import java.util.Locale;

public abstract class Piece {
    private final String name;

    public Piece(final String initialName) {
        name = initialName;
    }

    public Piece(final boolean isBlack, final String initialName) {
        if (isBlack) {
            name = initialName.toUpperCase();
            return;
        }
        name = initialName.toLowerCase(Locale.ROOT);
    }

    public String getName() {
        return name;
    }
}
