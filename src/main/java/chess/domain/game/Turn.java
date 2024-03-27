package chess.domain.game;

import chess.domain.pieces.piece.Color;

import java.util.stream.IntStream;

public class Turn {
    private Color turn;

    private Turn() {
        turn = Color.WHITE;
    }

    public static Turn first() {
        return new Turn();
    }

    public void next() {
        turn = turn.reverse();
    }

    public boolean isTurn(final Color color) {
        return this.turn == color;
    }

    public void proceedTurn(final int size) {
        IntStream.range(0, size)
                .forEach(i -> next());
    }

    public String getColor() {
        return turn.name();
    }
}
