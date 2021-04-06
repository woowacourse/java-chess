package chess.domain.game;

import chess.domain.result.BoardResult;
import chess.domain.state.Start;
import chess.domain.state.State;

public class WebChessGame {

    private State state;

    public WebChessGame() {
        this.state = new Start();
        state.receive("start");
        state = state.next();
    }

    public boolean isMovable(final String command) {
        state.receive(command);
        return state.isMovable();
    }

    public BoardResult move(final String command) {
        state.receive(command);
        state = state.next();
        BoardResult boardResult;
        try {
            boardResult = (BoardResult) state.bringResult();
            state = state.next();
        } catch (RuntimeException e) {
            state = state.before();
            throw new RuntimeException(e.getMessage());
        }
        return boardResult;
    }

    public boolean isRunning() {
        return state.isRunning();
    }
}
