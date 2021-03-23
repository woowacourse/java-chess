package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public abstract class Piece {
    private static final Pattern PATTERN = Pattern.compile("[A-Z]");

    private final String piece;
    private final Color color;

    private Position position;

    protected Piece(final String piece, final Position position, final Color color) {
        this.piece = piece;
        this.position = position;
        this.color = color;
    }

    public static boolean isBlack(final String piece) {
        return PATTERN.matcher(piece).matches();
    }

    public final boolean isBlack() {
        return color.equals(Color.BLACK);
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

    public final boolean isSameColor(final Piece piece) {
        return color.equals(piece.color);
    }

    public final File getFile() {
        return position.getFile();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece1 = (Piece) o;
        return Objects.equals(piece, piece1.piece) && color == piece1.color && Objects.equals(position, piece1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, color, position);
    }

    public abstract void move(final Target target, final Pieces basePieces, final Pieces targetPieces);

    public abstract double score(final List<Piece> pieces);

    public abstract boolean isKing();

    public abstract boolean isPawn();
}
