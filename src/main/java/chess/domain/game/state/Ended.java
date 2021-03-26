package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Ended extends Started {
    public Ended(Board board) {
        super(board);
    }
}
