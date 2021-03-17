package chess.domain.piece;

import chess.domain.position.Position;

import java.util.regex.Pattern;

public abstract class Piece {
    private static final Pattern PATTERN = Pattern.compile("A-Z");

    private final String piece;
    private final boolean isBlack;

    private final Position position;

    protected Piece(final String piece, final boolean isBlack, final Position position) {
        this.piece = piece;
        this.isBlack = isBlack;
        this.position = position;
    }

    public static boolean isBlack(final String piece) {
        return PATTERN.matcher(piece).matches();
    }

    public final boolean isSamePosition(final Position position) {
        return this.position.equals(position);
    }
}
