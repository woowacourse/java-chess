package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.role.Role;
import chess.domain.position.Position;

import java.util.Objects;

public final class Piece {

    private static final String PAWN_WRONG_DIRECTION = "폰은 전진만 가능합니다.";

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

    public void checkMovable(Position source, Position target) {
        role.checkMovable(source, target);
        if (role.isPawn()) {
            checkPawnDirection(source, target);
        }
    }

    public boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isPawn() {
        return role.isPawn();
    }

    public boolean isKing() {
        return role.isKing();
    }

    public double score() {
        return role.score();
    }

    private void checkPawnDirection(Position source, Position target) {
        if (!(isRightBlackDirection(source, target) || isRightWhiteDirection(source, target))) {
            throw new IllegalArgumentException(PAWN_WRONG_DIRECTION);
        }
    }

    private boolean isRightWhiteDirection(Position source, Position target) {
        return color.isWhite() && source.isBelow(target);
    }

    private boolean isRightBlackDirection(Position source, Position target) {
        return color.isBlack() && source.isAbove(target);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && piece.role.getClass() == role.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, role);
    }
}
