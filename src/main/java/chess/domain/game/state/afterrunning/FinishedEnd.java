package chess.domain.game.state.afterrunning;

import chess.domain.board.Board;
import chess.domain.game.state.State;

public class FinishedEnd extends AfterRunning {

    public FinishedEnd(Board board) {
        super(board);
    }

    @Override
    public State endGame() {
        return this;
    }

}
