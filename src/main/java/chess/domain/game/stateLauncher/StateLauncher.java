package chess.domain.game.stateLauncher;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public abstract class StateLauncher {

    protected final ChessGame chessGame;
    protected boolean run = true;

    public StateLauncher(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public StateLauncher go(String input) {
        try {
            return execute(input);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return this;
        }
    }

    protected abstract StateLauncher execute(String input);

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
