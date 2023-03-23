package chess.controller.state;

import chess.view.InputView;
import chess.view.OutputView;

public final class End implements State {

    @Override
    public State execute(InputView inputView, OutputView outputView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
