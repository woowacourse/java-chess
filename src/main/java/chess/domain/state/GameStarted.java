package chess.domain.state;

import chess.domain.board.Board;

public abstract class GameStarted implements GameState {

    protected final Board board;

    public GameStarted(Board board) {
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
