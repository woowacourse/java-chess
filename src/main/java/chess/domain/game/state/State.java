package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public abstract class State {

    protected final ChessGame chessGame;
    protected boolean run = true;

    public State(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public State go(String input) {
        try {
            return execute(input);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return this;
        }
    }

    protected abstract State execute(String input);

    public boolean isRun() {
        return run;
    }

    public boolean isPlay() {
        return false;
    }

    public boolean isStatus() {
        return false;
    }
}
