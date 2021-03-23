package chess.domain.gamestate.running;

import chess.domain.board.Board;
import chess.domain.gamestate.AbstractState;

public abstract class Running extends AbstractState {

    public Running(Board board) {
        super(board);
    }

    @Override
    public boolean isFinished() {
        if (board.isAnyKingDead()) {
            return true;
        }
        return false;
    }
}
