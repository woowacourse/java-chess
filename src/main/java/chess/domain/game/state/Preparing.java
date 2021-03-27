package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Preparing extends AfterStart {

    public Preparing(Board board) {
        super(board);
    }
}
