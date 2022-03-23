package chess.game;

import chess.board.Board;

public abstract class Started implements GameState {

    protected final Board board;

    public Started(Board board) {
        this.board = board;
    }
}
