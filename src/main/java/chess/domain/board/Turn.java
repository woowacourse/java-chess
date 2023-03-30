package chess.domain.board;

import chess.domain.piece.Color;

import java.util.Objects;

public class Turn {
    private static final Turn whiteTurn = new Turn(Color.WHITE);
    private static final Turn blackTurn = new Turn(Color.BLACK);

    private final Color turn;

    public Turn(final Color turn) {
        this.turn = turn;
    }

    public Turn change() {
        if (equals(whiteTurn)) {
            return blackTurn;
        }
        return whiteTurn;
    }

    public boolean isIncorrect(final Color color) {
        return turn != color;
    }

    public String convertToColorLabel() {
        return turn.label();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Turn)) return false;
        final Turn turn1 = (Turn) o;
        return turn == turn1.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn);
    }
}
