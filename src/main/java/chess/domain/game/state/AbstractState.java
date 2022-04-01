package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.dto.CommandDto;
import chess.view.OutputView;

public abstract class AbstractState implements State {
    protected final ChessGame chessGame;

    public AbstractState(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public State go(String input) {
        try {
            return execute(new CommandDto(input));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return this;
        }
    }

    public boolean isRun() {
        return true;
    }

    public boolean isPlay() {
        return false;
    }

    public boolean isStatus() {
        return false;
    }
}
