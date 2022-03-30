package chess.domain.game.state.running;

import chess.domain.board.Board;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.game.state.afterrunning.FinishedEnd;

public abstract class Running extends Started {

    protected Running(Board board) {
        super(board);
    }

    @Override
    public State startGame() {
        throw new IllegalStateException("이미 게임 중 입니다.");
    }

    @Override
    public State endGame() {
        return new FinishedEnd(getBoard());
    }
}
