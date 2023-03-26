package controller;

import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Consumer;

public final class ChessGameController {

    private final GameStatus gameStatus;

    public ChessGameController(final GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void run() {
        OutputView.printStartMessage();

        while (gameStatus.isKeepGaming()) {
            retryOnError(gameStatus::playTurn);
        }
    }

    private void retryOnError(final Consumer<List<String>> playGame) {
        try {
            playGame.accept(InputView.readline());
        } catch (IllegalArgumentException | IllegalStateException | IndexOutOfBoundsException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

}
