package chess.controller;

import chess.view.input.InputView;
import chess.view.output.OutputView;

import java.util.Objects;
import java.util.function.Supplier;

public class ChessGame {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        GameState gameState = retryOnException(this::prepare);
        retryOnException(() -> play(gameState));
    }

    private GameState prepare() {
        GameState prepare = new Prepare();
        return prepare.run(inputView, outputView);
    }

    private void play(GameState prepare) {
        GameState gameState = prepare;
        while (gameState.canContinue()) {
            gameState = gameState.run(inputView, outputView);
        }
    }

    private <T> T retryOnException(Supplier<T> retryOperation) {
        boolean retry = true;
        T result = null;
        while (retry) {
            result = tryOperation(retryOperation);
            retry = Objects.isNull(result);
        }
        return result;
    }

    private <T> T tryOperation(Supplier<T> operation) {
        try {
            return operation.get();
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return null;
        }
    }

    private void retryOnException(Runnable retryOperation) {
        boolean retry = true;
        while (retry) {
            retry = tryOperation(retryOperation);
        }
    }

    private boolean tryOperation(Runnable operation) {
        try {
            operation.run();
            return false;
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return true;
        }
    }
}
