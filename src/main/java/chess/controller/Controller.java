package chess.controller;

import chess.view.InputView;
import chess.view.OutputView;

public abstract class Controller {

    protected final InputView inputView;
    protected final OutputView outputView;

    protected Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public abstract void run();

    public static Controller create(InputView inputView, OutputView outputView) {
        return new UserController(inputView, outputView);
    }
}
