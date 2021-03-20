package chess.domain.gamestate;

import chess.domain.Board;

public abstract class GameState implements State {
    private final Board board;

    protected GameState(Board board) {
        this.board = board;
    }

    protected Board board() {
        return board;
    }
}
