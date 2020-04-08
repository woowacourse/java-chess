package chess.domain;

import chess.domain.piece.Color;

import java.util.Objects;

public class Turn {

    private Color turn;

    Turn(Color color) {
        this.turn = color;
    }

    void changeTurn(String color) {
        if (Color.of(color).equals(turn)) {
            turn = turn.changeColor(turn);
        }
    }


    public Color getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn1 = (Turn) o;
        return turn == turn1.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn);
    }
}
