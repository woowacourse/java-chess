package controller;

import service.ChessService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Consumer;

public final class ChessGameController {

    private final ChessService chessService;

    public ChessGameController(final ChessService chessService) {
        this.chessService = chessService;
    }


    public void run() {
        OutputView.printStartMessage();

        final GameStatus gameStatus = new GameStatus(chessService);

        do {
            retryOnError(gameStatus::playTurn);
        } while (gameStatus.isKeepGaming());
        gameStatus.noticeKingDead();
    }

    private void retryOnError(final Consumer<List<String>> playGame) {
        try {
            playGame.accept(InputView.readline());
        } catch (IllegalArgumentException | IllegalStateException | IndexOutOfBoundsException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

}
