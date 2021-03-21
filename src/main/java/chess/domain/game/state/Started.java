package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Started implements State {

    private final Board board;

    public Started(Board board) {
        this.board = board;
    }

    public Board board(){
        return this.board;
    }
}
