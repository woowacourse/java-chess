package chess.domain.piece;

import java.util.regex.Pattern;

public abstract class Piece {
    private static final Pattern PATTERN = Pattern.compile("A-Z");

    private final String piece;
    private final boolean isBlack;

    protected Piece(final String piece, final boolean isBlack) {
        this.piece = piece;
        this.isBlack = isBlack;
    }

    public static boolean isBlack(String piece) {
        return PATTERN.matcher(piece).matches();
    }
}
