package chess.domain;

import chess.domain.rule.Empty;
import chess.domain.rule.Pawn;

import java.util.Objects;

public class Piece {
    public enum Color {
        WHITE("WHITE"),
        BLACK("BLACK"),
        EMPTY("EMPTY");

        private final String name;

        Color(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final Position position;
    private final Color color;

    private final Rule rule;

    public Piece(final Position position, final Color color, final Rule rule) {
        this.position = position;
        this.color = color;
        this.rule = rule;
    }

    public static Piece of(final Position position, final Color color, final Rule rule) {
        return new Piece(position, color, rule);
    }

    static Piece empty(final Position position) {
        return new Piece(position, Color.EMPTY, Empty.getInstance());
    }

    boolean isSameColor(final Color other) {
        return color == other;
    }

    boolean isSameColor(final Piece other) {
        return this.color == other.color;
    }

    boolean isSameColumn(final Column other) {
        return this.position.isSameColumn(other);
    }

    public boolean isValidMove(final Piece other) {
        return rule.isValidMove(this.position, other.position);
    }

    boolean isValidAttack(final Piece other) {
        return !isSameColor(other) && rule.isValidAttack(this.position, other.position);
    }

    boolean isEmpty() {
        return Color.EMPTY == color;
    }

    boolean isPawn() {
        return this.rule.isSameType(Rule.Type.PAWN);
    }

    boolean isKing() {
        return this.rule.isSameType(Rule.Type.KING);
    }

    //TODO 리팩토링
    public boolean isSameType(final Rule.Type other) {
        return rule.isSameType(other);
    }

    //TODO 리팩토링
    Piece get(final Position position) {
        Rule rule = this.rule;
        if (this.rule == Pawn.FIRST_BOTTOM) {
            rule = Pawn.SECOND_BOTTOM;
        }
        if (this.rule == Pawn.FIRST_TOP) {
            rule = Pawn.SECOND_TOP;
        }
        return Piece.of(position, this.color, rule);
    }

    public double getScore() {
        return rule.getScore();
    }

    public String getSymbol() {
        return PieceSymbol.getSymbol(this);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return Objects.equals(position, piece.position) &&
                color == piece.color &&
                Objects.equals(rule, piece.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color, rule);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", color=" + color +
                ", rule=" + rule +
                '}';
    }
}
