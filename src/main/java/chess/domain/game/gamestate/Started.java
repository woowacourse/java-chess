package chess.domain.game.gamestate;

import chess.domain.board.Board;

abstract class Started implements State {

    private final Board board;

    Started(Board board) {
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
