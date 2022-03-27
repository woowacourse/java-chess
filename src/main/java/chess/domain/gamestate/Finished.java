package chess.domain.gamestate;

import chess.domain.board.Board;

public class Finished implements State {
    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new Running(new Board());
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
