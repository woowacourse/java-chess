package chess.domain.game.state.running;

import chess.domain.board.Board;
import chess.domain.game.state.beforerunning.FinishedStatus;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;

public abstract class Running extends Started {

    public Running(Board board) {
        super(board);
    }

    @Override
    public State startGame() {
        throw new IllegalStateException("이미 게임 중 입니다.");
    }

    @Override
    public State endGame() {
        return new FinishedStatus(getBoard());
    }

    @Override
    public State showStatus() {
        return new FinishedStatus(getBoard());
    }

}
