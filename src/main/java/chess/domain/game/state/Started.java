package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Started implements State {

    protected final Board board;

    protected Started(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
