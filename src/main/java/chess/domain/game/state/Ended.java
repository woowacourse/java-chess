package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Ended extends AfterStart {

    public Ended(Board board) {
        super(board);
    }
}
