package chess.domain.board;

import chess.domain.piece.Color;

import java.util.Objects;

public class Turn {

    private static final Turn whiteTurn = new Turn(Color.WHITE);
    private static final Turn blackTurn = new Turn(Color.BLACK);

    private final Color color;

    public Turn(final Color color) {
        this.color = color;
    }

    public Turn change() {
        if (equals(whiteTurn)) {
            return blackTurn;
        }
        return whiteTurn;
    }

    public boolean misMatch(final Color color) {
        return this.color != color;
    }

    public Color enemyColor() {
        if (equals(whiteTurn)) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Turn)) return false;
        final Turn turn1 = (Turn) o;
        return color == turn1.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    public Color color() {
        return color;
    }
}
