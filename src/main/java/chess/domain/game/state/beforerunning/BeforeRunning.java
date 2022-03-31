package chess.domain.game.state.beforerunning;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.game.state.running.RunningWhite;

public abstract class BeforeRunning extends Started {

    protected BeforeRunning(Board board) {
        super(board);
    }

    @Override
    public State startGame() {
        return new RunningWhite(BoardFactory.createInitializedBoard());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
