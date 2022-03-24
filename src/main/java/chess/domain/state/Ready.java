package chess.domain.state;

import chess.domain.board.Board;

abstract class Ready implements State {

    protected final Board board;

    protected Ready(Board board) {
        this.board = board;
    }

    public static State start(Board board) {
        return new WhiteTurn(board);
    }
}
