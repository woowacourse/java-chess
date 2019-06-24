package chess.domain;

import chess.domain.rule.Empty;
import chess.domain.rule.King;
import chess.domain.rule.Pawn;

import java.util.Objects;

public class Piece {
    private static final Piece EMPTY = new Piece(Color.EMPTY, Empty.getInstance());

    private Color color;
    private Rule rule;

    private Piece(final Color color, final Rule rule) {
        this.color = color;
        this.rule = rule;
    }

    public static Piece of(final Color color, final Rule rule) {
        return new Piece(color, rule);
    }

    static Piece empty() {
        return EMPTY;
    }

    boolean isSameColor(final Color other) {
        return color == other;
    }

    boolean isSameColor(final Piece other) {
        return this.color == other.color;
    }

    public boolean isValidMove(final Position origin, final Position target) {
        return rule.isValidMove(origin, target);
    }

    boolean isValidAttack(final Position origin, final Position target) {
        return rule.isValidAttack(origin, target);
    }

    boolean isEmpty() {
        return Color.EMPTY == color;
    }

    boolean isPawn() {
        for (Rule rule : Pawn.values()) {
            if (this.rule == rule) {
                return true;
            }
        }
        return false;
    }

    boolean isKing() {
        return this.rule == King.getInstance();
    }

    Piece get() {
        if (this.rule == Pawn.FIRST_BOTTOM) {
            return Piece.of(this.color, Pawn.SECOND_BOTTOM);
        }
        if (this.rule == Pawn.FIRST_TOP) {
            return Piece.of(this.color, Pawn.SECOND_TOP);
        }
        return Piece.of(this.color, this.rule);
    }

    public double getScore() {
        return rule.getScore();
    }

    String getSymbol() {
        return PieceSymbol.getSymbol(this.color, this.rule);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return color == piece.color &&
                Objects.equals(rule, piece.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, rule);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", rule=" + rule +
                '}';
    }

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
}
