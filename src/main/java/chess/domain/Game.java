package chess.domain;

import java.util.Objects;

public class Game {

    private final Board board;

    public Game(final Board board) {
        this.board = board;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Game game = (Game) o;
        return Objects.equals(board, game.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
