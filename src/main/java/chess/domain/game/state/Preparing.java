package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Preparing extends Started {

    public Preparing(Board board) {
        super(board);
    }
}
