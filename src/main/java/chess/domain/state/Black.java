package chess.domain.state;

import chess.domain.Board;

public class Black extends Started {

    public Black(final Board board) {
        super(board);
    }

    @Override
    public State changeTurn() {
        return new White(board());
    }
}
