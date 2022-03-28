package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Started implements State {

    private final Board board;

    protected Started(Board board) {
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
