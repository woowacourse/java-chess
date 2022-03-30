package chess.domain;

import chess.domain.pieces.Role;
import chess.domain.position.Position;

import java.util.Objects;

public final class Piece {

    private final Color color;
    private final Role role;

    public Piece(Color color, Role role) {
        this.color = color;
        this.role = role;
    }

    public String symbol() {
        if (color.isWhite()) {
            return role.getSymbol().toLowerCase();
        }
        return role.getSymbol();
    }

    public boolean isMovable(Position source, Position target) {
        boolean movable = role.isMovable(source, target);
        if (movable && role.isPawn()) {
            return checkPawnDirection(source, target);
        }
        return movable;
    }

    private boolean checkPawnDirection(Position source, Position target) {
        if (color.isBlack()) {
            return source.isAbove(target);
        }
        return source.isBelow(target);
    }

    public boolean isPawn() {
        return role.isPawn();
    }

    public boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isKing() {
        return role.isKing();
    }

    public double score() {
        return role.score();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return color == piece.color && piece.role.getClass() == role.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, role);
    }
}
