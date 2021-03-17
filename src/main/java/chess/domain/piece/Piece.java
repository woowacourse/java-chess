package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Objects;
import java.util.regex.Pattern;

public abstract class Piece {
    private static final Pattern PATTERN = Pattern.compile("A-Z");

    private final String piece;
    private final boolean isBlack;

    private Position position;

    protected Piece(final String piece, final boolean isBlack, final Position position) {
        this.piece = piece;
        this.isBlack = isBlack;
        this.position = position;
    }

    public static boolean isBlack(final String piece) {
        return PATTERN.matcher(piece).matches();
    }

    public final Position getPosition() {
        return position;
    }

    public final String getPiece() {
        return piece;
    }

    public final boolean isSamePosition(final Position position) {
        return this.position.equals(position);
    }

    public final void changePosition(final Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece1 = (Piece) o;
        return isBlack == piece1.isBlack && Objects.equals(piece, piece1.piece) && Objects.equals(position, piece1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, isBlack, position);
    }
}
