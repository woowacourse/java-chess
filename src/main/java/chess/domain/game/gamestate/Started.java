package chess.domain.game.gamestate;

import chess.domain.board.Board;

abstract class Started implements State {

    private final Board board;

    Started() {
        this.board = Board.createInitializedBoard();
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
