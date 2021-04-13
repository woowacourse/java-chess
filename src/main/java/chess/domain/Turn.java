package chess.domain;

import chess.domain.board.Team;

import java.util.Objects;

public class Turn {

    private boolean isWhiteTurn;

    public Turn() {
        this.isWhiteTurn = true;
    }

    public Team now() {
        if (isWhiteTurn) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public void nextTurn() {
        isWhiteTurn = !isWhiteTurn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return isWhiteTurn == turn.isWhiteTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isWhiteTurn);
    }
}
