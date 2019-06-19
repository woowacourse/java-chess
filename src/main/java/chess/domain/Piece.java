package chess.domain;

import chess.domain.RuleImpl.Empty;
import chess.domain.RuleImpl.Rule;

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

    public static Piece empty() {
        return EMPTY;
    }

    public boolean isValidMove(final Position origin, final Position target) {
        return rule.isValidMove(origin, target);
    }

    public boolean isEmpty() {
        return Color.EMPTY == color;
    }

    public boolean isSameTeam(final Piece other) {
        return this.color == other.color;
    }

    public enum Color {
        WHITE(1),
        BLACK(2),
        EMPTY(3);

        private final int index;
        Color(final int index) {
            this.index = index;
        }

    }
}
