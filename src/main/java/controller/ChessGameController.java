package controller;

import domain.chessboard.ChessBoardFactory;
import domain.chessgame.ChessGame;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Consumer;

public final class ChessGameController {

    private GameStatus gameStatus;

    public void run() {
        OutputView.printStartMessage();
        gameStatus = new Start(new ChessGame(ChessBoardFactory.generate()));

        while (gameStatus.isKeepGaming()) {
            retryOnError(this::playGame);
        }
    }

    private void playGame(final List<String> inputs) {
        gameStatus = gameStatus.transition(inputs);
        final ChessBoardDTO chessBoardDTO = gameStatus.playTurn(inputs);

        OutputView.printChessBoard(chessBoardDTO);
    }

    private void retryOnError(final Consumer<List<String>> playGame) {
        try {
            playGame.accept(InputView.readline());
        } catch (IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

}
