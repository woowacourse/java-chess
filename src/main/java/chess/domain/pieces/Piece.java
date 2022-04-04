package chess.domain.pieces;

import chess.domain.position.Position;

import java.util.Objects;

public final class Piece {

    private static final String WHITE_SYMBOL = "white";
    private static final String BLACK_SYMBOL = "black";

    private final Color color;
    private final Type type;

    public Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public String symbol() {
        if (color.isWhite()) {
            return WHITE_SYMBOL + type.symbol().value();
        }
        return BLACK_SYMBOL + type.symbol().value();
    }

    public boolean isMovable(final Position source, final Position target) {
        final boolean movable = type.isMovable(source, target);
        if (movable && type.isPawn()) {
            return checkPawnDirection(source, target);
        }
        return movable;
    }

    private boolean checkPawnDirection(final Position source, final Position target) {
        if (color.isBlack()) {
            return source.isAbove(target);
        }
        return source.isBelow(target);
    }

    public boolean isPawn() {
        return type.isPawn();
    }

    public boolean isSameColorPiece(final Piece piece) {
        return color == piece.color;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public boolean isKing() {
        return type.isKing();
    }

    public double score() {
        return type.score();
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return color == piece.color && piece.type.getClass() == type.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
