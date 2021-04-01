package chess.domain.game;

import chess.domain.result.Result;
import chess.domain.state.ResultType;
import chess.domain.state.Start;
import chess.domain.state.State;
import java.util.Objects;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Start();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean needsCommand() {
        return state.needsParam();
    }

    public void receiveCommand(final String command) {
        state.receive(command);
    }

    public boolean supports(final ResultType board) {
        return Objects.equals(board, state.bringResultType());
    }

    public Result bringResult() {
        return state.bringResult();
    }

    public void progress(final boolean isSuccessful) {
        if (isSuccessful) {
            state = state.next();
            return;
        }
        state = state.before();
    }
}

