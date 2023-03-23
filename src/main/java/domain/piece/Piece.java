package domain.piece;

import domain.Location;
import domain.Section;
import domain.type.Color;
import domain.type.PieceType;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected static final String IMPOSSIBLE_MOVE_ERROR_MESSAGE = "이동할 수 없는 위치입니다.";
    protected final Color color;
    protected final PieceType pieceType;

    protected Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    abstract public List<Location> searchPath(final Section start, final Section end);

    abstract protected boolean isNotMovable(final Section start, final Section end);

    public boolean isSameType(final PieceType pieceType) {
        return this.pieceType.equals(pieceType);
    }

    public boolean isDifferentType(final PieceType pieceType) {
        return !this.pieceType.equals(pieceType);
    }

    public boolean isEnemy(final Piece piece) {
        return piece.isDifferentType(PieceType.EMPTY) && isDifferentColor(piece);
    }

    public boolean isDifferentColor(final Piece piece) {
        return !color.equals(piece.color);
    }

    public boolean isDifferentColor(final Color color) {
        return !this.color.equals(color);
    }

    public boolean isSameColor(final Piece piece) {
        return color.equals(piece.color);
    }

    public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isNotEmpty() {
        return !pieceType.equals(PieceType.EMPTY);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
