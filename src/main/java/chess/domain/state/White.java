package chess.domain.state;

import chess.domain.Board;

public class White extends Started {

    public White(final Board board) {
        super(board);
    }

    @Override
    public State changeTurn() {
        return new Black(board());
    }
}
