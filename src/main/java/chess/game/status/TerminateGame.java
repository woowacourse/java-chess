package chess.game.status;

import chess.view.input.InputView;
import chess.view.output.OutputView;

public class TerminateGame implements GameStatus {

    @Override
    public boolean isPlayable() {
        return false;
    }

    @Override
    public GameStatus play(final InputView inputView, final OutputView outputView) {
        return this;
    }
}
