package chess.domain.state;

import chess.domain.Board;

public class Black extends Running {
    public Black(Board board) {
        super(board);
    }

    @Override
    public State move() {
        return new White(getBoard());
    }
}
