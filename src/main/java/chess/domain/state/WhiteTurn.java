package chess.domain.state;

import chess.domain.board.Board;

public final class WhiteTurn extends Playing {

    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public boolean isBlackTurn() {
        return false;
    }
}
