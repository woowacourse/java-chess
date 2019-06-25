package chess.model;

import java.util.Objects;
import java.util.prefs.Preferences;

public abstract class AbstractChessPiece {
    private ChessPieceType type;
    private ChessPieceColor color;

    protected AbstractChessPiece(ChessPieceType type, ChessPieceColor color) {
        this.type = type;
        this.color = color;
    }

    public static AbstractChessPiece getInstance(final ChessPieceType type, final ChessPieceColor color) {
        if (type == ChessPieceType.PAWN) {
            return AbstractPawn.getInstance(color);
        }

        if (type == ChessPieceType.QUEEN) {
            return new Queen(color);
        }

        if (type == ChessPieceType.KING) {
            return new King(color);
        }

        if (type == ChessPieceType.ROOK) {
            return new Rook(color);
        }

        if (type == ChessPieceType.KNIGHT) {
            return new Knight(color);
        }

        if (type == ChessPieceType.BISHOP) {
            return new Bishop(color);
        }

        throw new IllegalArgumentException();
    }

    public abstract boolean canMove(Point source, Point target, AbstractBoardNavigator navigator);

    public boolean isSameColor(ChessPieceColor color) {
        return this.color.equals(color);
    }

    public boolean isType(ChessPieceType type) {
        return this.type.equals(type);
    }

    public abstract double getScore(Point point, AbstractBoardNavigator navigator);

    public ChessPieceColor getColor() {
        return color;
    }

    public ChessPieceType getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractChessPiece that = (AbstractChessPiece) o;
        return type == that.type &&
                color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }
}
