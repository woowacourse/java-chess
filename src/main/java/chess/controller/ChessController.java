package chess.controller;

import chess.controller.state.Ready;
import chess.controller.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        State state = new Ready().execute(inputView, outputView);
        while (state.isRunning()) {
            state = state.execute(inputView, outputView);
        }
    }
}
